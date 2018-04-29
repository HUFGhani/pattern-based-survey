package man.survey;

import java.util.List;

/**
 * Created by swsmi on 2018/4/29.
 */
public class CompositeQuestion extends Question {
    private List<Question> question;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getQuestionText() {
        return null;
    }

    @Override
    public void setQuestionText(String questionText) {

    }

    @Override
    public Answer getAnswer() {
        return null;
    }

    @Override
    public void setAnswer(Answer answer) {

    }
}
