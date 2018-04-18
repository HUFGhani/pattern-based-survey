package survey;

import java.util.List;
import java.util.Map;

public abstract class Question {

    public abstract int getId();

    public abstract void setId(int id);

    public abstract String getQuestionText();

    public abstract void setQuestionText(String questionText);

    public abstract Answer getAnswer();

    public abstract void setAnswer(Answer answer);


}
