package man.survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swsmi on 2018/4/29.
 */
public class CompositeQuestion extends Question {
    private List<Question> list;
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id =id;

    }

    @Override
    public List<Question> getQuestions() {
        return list;
    }

    @Override
    public Answer getAnswer() {
        return null;
    }

    @Override
    public void setAnswer(Answer answer) {

    }
}
