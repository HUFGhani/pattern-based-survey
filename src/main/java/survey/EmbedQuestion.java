package survey;

import java.util.List;

public class EmbedQuestion extends Question {

    private List<Alternative> alternatives;
    private int id;
    private String questionText;
    private String embedLink;
    private Answer answer;

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

    public EmbedQuestion(List<Alternative> alternatives, int id, String questionText, String embedLink, Answer answer) {
        this.alternatives = alternatives;
        this.id = id;
        this.questionText = questionText;
        this.embedLink = embedLink;
        this.answer = answer;
    }

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
