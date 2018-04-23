package man.darksky;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import man.config.ConfigurationProperties;
import man.survey.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import savesurvery.SurveryWriterCaretaker;
import savesurvery.SurveryWriterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SurveyController {

    SurveyFactory surveyFactory;
    ConfigurationProperties configurationProperties;

    @Autowired
    private Twitter twitter;

    @Autowired
    private DarkSkyApiAdapter darkSkyApiService;

    @Autowired
    public SurveyController (SurveyFactory surveyFactory, ConfigurationProperties configurationProperties){
        this.configurationProperties = configurationProperties;
        this.surveyFactory = surveyFactory;
    }

    @RequestMapping("/survey")
    public String showSurvey(Model model, HttpServletRequest request,
                             @RequestParam(value = "surveyName", required = false) String surveyName){
        int currentSection = 0;
        if(surveyName == null){
            surveyName=configurationProperties.getDefaultSurveyName();
        }
        Survey survey = surveyFactory.getSurvey(surveyName);

        EmbedQuestionAdapter adapter = Calendar.getInstance().getTimeInMillis() % 2 == 0 ?
                new EmbedQuestionAdapterTwitter(twitter) : new EmbedQuestionAdapterWeather(darkSkyApiService); // Get random embedded content

        for (Section s : survey.getSections()) // Invokes adapter to obtain a link for embedding Tweet/Weather content
            for (Question q: s.getQuestions())
                if (q instanceof EmbedQuestion)
                    ((EmbedQuestion) q).setEmbedLink(adapter.getEmbedLink());

        model.addAttribute("questions",survey.getSectionById(currentSection).getQuestions());
        model.addAttribute("sectionId",survey.getSectionById(currentSection).getId());
        request.getSession().setAttribute("survey",survey);
        return "survey";
    }


    @PostMapping("/survey")
    public String testSurvey(@RequestParam Map<String,String> params, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute("survey");
        int currentSection = Integer.parseInt(params.get("sectionId"));
        HashMap<Integer,String> answers = new HashMap<>();

        survey.getSectionById(currentSection).getQuestions().stream().forEach(q->answers.put(q.getId(),params.get((Integer.toString(q.getId())))));
        answers.forEach((id,answer)->survey.getSections()
                        .get(currentSection)
                        .getQuestionById(id)
                        .setAnswer(new Answer(answer)));

        if (!isEndOfSurvey(survey,currentSection)) {
            session.setAttribute("survey", survey);
            model.addAttribute("sectionId", currentSection + 1);
            model.addAttribute("questions", survey.getSectionById(currentSection + 1).getQuestions());
        }else{
            saveSurvey(survey,request);
            return showEndPage();
        }

        return "survey";
    }

    private String showEndPage(){
        return "endpage";
    }

    private void saveSurvey(Survey survey,HttpServletRequest request){
        //TODO: Implement saving of survey.

        //TODO: Probably move this to another class?
        ObjectMapper mapper = new ObjectMapper();
        Survey s = survey;
        String jsonInString = null;

        try {
           jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SurveryWriterCaretaker caretaker = new SurveryWriterCaretaker();
        SurveryWriterUtil fileWriter = new SurveryWriterUtil("survey/"+request.getSession().getId()+".json");
        fileWriter.write(jsonInString);
        System.out.println(fileWriter);
        caretaker.save(fileWriter);
    }


    private boolean isEndOfSurvey(Survey survey,int currentSection){
        //TODO: Move this to somewhere, does not belong in controller?
        return currentSection >= survey.getSections().size()-1;
    }

    @RequestMapping("/results")
    public ResponseEntity<Survey> results(HttpServletRequest request){
        //TODO: This doesn't have to be here by the end, just added for easy access to example of JSON results.
        //TODO:Remove button for results in "endpage.html" as well.
        Survey survey = (Survey) request.getSession().getAttribute("survey");
        return new ResponseEntity(survey,HttpStatus.OK);

    }

}
