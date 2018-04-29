package man.survey;

import java.util.List;

/**
 * Created by swsmi on 2018/4/29.
 */
public class CompositeQuestion extends Question {
    private List<Question> question;
    private int id;
    private String questionText;
    private Answer answer;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id =id;

    }
}
