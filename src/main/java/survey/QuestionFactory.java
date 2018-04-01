package survey;

import java.util.List;

public class QuestionFactory {


    public Question getQuestion(String questionType, int id, String questionText, List<Alternative> alternatives, int scale){
        if(questionType == "matrix"){
            return new MatrixQuestion();
        }else if(questionType == "mcq"){
            return new McQuestion();
        }else if(questionType == "text"){
            return new TextQuestion();
        }
        return null;
    }


}
