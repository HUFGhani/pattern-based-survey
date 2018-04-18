package survey;

import java.util.List;

public class EmbedQuestion extends Question {

    private List<Alternative> alternatives;
    private int id;
    private String questionText;
    private String embedLink;
    private Answer answer;

    public EmbedQuestion( int id, String questionText, List<Alternative> alternatives, String embedLink) {
        this.alternatives = alternatives;
        this.id = id;
        this.questionText = questionText;
        this.embedLink = embedLink;
        this.answer = answer;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public String getEmbedLink() {
        return embedLink;
    }

    public void setEmbedLink(String embedLink) {
        this.embedLink = embedLink;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public Answer getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
