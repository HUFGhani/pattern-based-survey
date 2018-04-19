package man.survey;

import java.util.List;

public class QuestionFactory {

    public Question getQuestion(String questionType, int id, String questionText, List<Alternative> alternatives, int scale, String embedLink){

        if (questionType == "matrix") {

            return new MatrixQuestion(id , scale, questionText);

        } else if (questionType == "mcq") {

            return new McQuestion(id, alternatives, questionText);

        } else if (questionType == "text") {

            return new TextQuestion(id, questionText);

        } else if (questionType == "embed") {

            return new EmbedQuestion(id, scale, questionText, embedLink);
        }

        return null;
    }

}
