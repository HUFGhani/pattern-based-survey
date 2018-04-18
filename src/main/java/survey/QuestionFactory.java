package survey;

import java.util.List;

public class QuestionFactory {

    public Question getQuestion(String questionType, int id, String questionText, List<Alternative> alternatives, int scale){

        if (questionType == "matrix") {

            return new MatrixQuestion(id , scale, questionText);

        } else if (questionType == "mcq") {

            return new McQuestion(id, alternatives, questionText);

        } else if (questionType == "text") {

            return new TextQuestion(id, questionText);
        }

        return null;
    }

}
