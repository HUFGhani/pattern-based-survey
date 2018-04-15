package darksky;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import survey.Question;
import survey.QuestionFactory;
import survey.Section;
import survey.Survey;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyController {


    @RequestMapping("/survey")
    public String testSurvey(Model model){

        List<Question> questions = new ArrayList<>();
        QuestionFactory qf = new QuestionFactory();
        Question q1 = qf.getQuestion("text", 1, "What question?",null,0);
        questions.add(q1);

        List<Section> sections = new ArrayList<>();
        Section s1 = new Section();
        s1.setQuestions(questions);
        sections.add(s1);

        Survey survey = new Survey();
        survey.setSections(sections);

        model.addAttribute("questions",questions);


        return "survey";

    }


}
