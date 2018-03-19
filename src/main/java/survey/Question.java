package survey;

import java.util.List;
import java.util.Map;

public abstract class Question {

    int id;

    public abstract int getId();

    public abstract void setId(int id);

    public abstract String getQuestion();

    public abstract void setQuestion(String question);

    public abstract Answer getAnswer();

    public abstract void setAnswers(List<String> answers);


}
