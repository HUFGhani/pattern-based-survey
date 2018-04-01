package survey;

import java.util.List;
import java.util.Map;

public class McQuestion extends Question {

    private List<Alternative> alternatives;
    private int id;
    private String questionText;
    private Answer answer;


    public McQuestion(int id, List<Alternative> alternatives, String questionText) {
        this.id = id;
        this.alternatives = alternatives;
        this.questionText = questionText;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public String getQuestionText() {
        return this.questionText;
    }

    @Override
    public void setQuestionText(String questionText) {
        this.questionText=questionText;
    }

    @Override
    public Answer getAnswer() {
        return this.answer;
    }

    @Override
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
