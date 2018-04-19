package darksky;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import survey.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class SurveyController {

    @Autowired
    private TwitterApiService twitterApiService;

    @Autowired
    private DarkSkyApiService darkSkyApiService;

    @RequestMapping("/survey")
    public String showSurvey(Model model, HttpServletRequest request){
        int currentSection = 0;
        Survey survey = getSurvey();
        model.addAttribute("questions",survey.getSectionById(currentSection).getQuestions());
        model.addAttribute("sectionId",survey.getSectionById(currentSection).getId());
        request.getSession().setAttribute("survey",survey);
        return "survey";
    }

    public Survey getSurvey(){
        //TODO: Remove static survey implementation and add real survey from JSON file

        List<Question> questions = new ArrayList<>();
        QuestionFactory qf = new QuestionFactory();
        Question q1 = qf.getQuestion("text", 0, "What question?",null,0, null);
        questions.add(q1);


        Alternative alt1 = new Alternative(1,"First alternative");
        Alternative alt2 = new Alternative(2,"Second alternative");
        List<Alternative> alternatives = new ArrayList<>();
        alternatives.add(alt1);
        alternatives.add(alt2);
        Question q2 = qf.getQuestion("mcq",1,"Which alternative?",alternatives,0, null);
        questions.add(q2);

        Question q3 = qf.getQuestion("matrix",2,"How does this survey make you feel?",null,
                7, null);
        questions.add(q3);

        List<Section> sections = new ArrayList<>();
        Section s1 = new Section(0,questions);
        sections.add(s1);


        List<Question> questions2 = new ArrayList<>();
        Question q4 = qf.getQuestion("matrix",3,"How does this survey make you feel two?",
                null,7, null);
        Question q5 = qf.getQuestion("text",4,"More questions?",null,0, null);


        questions2.add(q4);
        questions2.add(q5);
        Section s2 = new Section(1,questions2);
        sections.add(s2);

        EmbedQuestionAdapter adapter = Calendar.getInstance().getTimeInMillis() % 2 == 0 ?
                new EmbedQuestionAdapterTwitter(twitterApiService) : new EmbedQuestionAdapterWeather(darkSkyApiService);

        List<Question> questions3 = new ArrayList<>();
        Question q6 = qf.getQuestion("embed", 5,
                "How does this content make you feel? (1 = very unhappy, 4 = neutral, 7 = very happy)",
                null, 7, adapter.getEmbedLink());

        questions3.add(q6);
        Section s3 = new Section(2, questions3);
        sections.add(s3);

        Survey survey = new Survey();
        survey.setSections(sections);
        return survey;
    }

    @PostMapping("/survey")
    public String testSurvey(@RequestParam Map<String,String> answers, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Survey survey = (Survey) session.getAttribute("survey");
        int currentSection = Integer.parseInt(answers.get("sectionId"));
        answers.remove("sectionId");

        answers.forEach((id,answer)->survey.getSections()
                        .get(currentSection)
                        .getQuestionById(Integer.parseInt(id))
                        .setAnswer(new Answer(answer)));

        if(!isEndOfSurvey(survey,currentSection)) {
            session.setAttribute("survey", survey);
            model.addAttribute("sectionId", currentSection + 1);
            model.addAttribute("questions", survey.getSectionById(currentSection + 1).getQuestions());
        }else{
            saveSurvey(survey);
            return showEndPage();
        }

        return "survey";
    }

    private String showEndPage(){
        return "endpage";
    }

    private void saveSurvey(Survey survey){
        //TODO: Implement saving of survey.
        //TODO: Probably move this to another class?
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
