
package man.survey;

public class EmbedQuestion extends Question {

    private int id;
    private String questionText;
    private String embedLink;
    private Answer answer;
    private int scale;

    public EmbedQuestion(){}

    public EmbedQuestion(int id, int scale, String questionText, String embedLink) {
        this.id = id;
        this.questionText = questionText;
        this.embedLink = embedLink;
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
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
