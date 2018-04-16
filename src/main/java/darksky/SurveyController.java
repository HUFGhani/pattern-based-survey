package darksky;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import survey.*;

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


        Alternative alt1 = new Alternative(1,"First alternative");
        Alternative alt2 = new Alternative(2,"Second alternative");
        List<Alternative> alternatives = new ArrayList<>();
        alternatives.add(alt1);
        alternatives.add(alt2);
        Question q2 = qf.getQuestion("mcq",2,"Which alternative?",alternatives,0);
        questions.add(q2);

        Question q3 = qf.getQuestion("matrix",2,"How does this survey make you feel?",null,7);
        questions.add(q3);

        List<Section> sections = new ArrayList<>();
        Section s1 = new Section();
        s1.setQuestions(questions);
        sections.add(s1);

        Survey survey = new Survey();
        survey.setSections(sections);

        model.addAttribute("questions",questions);


        return "survey";

    }

    public void getSurvey(){

    }


}
