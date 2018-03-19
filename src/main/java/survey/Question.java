package survey;

import java.util.List;

public abstract class Question {

    public abstract int getId();

    public abstract void setId(int id);

    public abstract String getQuestion();

    public abstract void setQuestion(String question);

    public abstract List<String> getAnswers();

    public abstract void setAnswers(List<String> answers);


}
