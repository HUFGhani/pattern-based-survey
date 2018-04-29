package man.survey;

public class TextQuestion extends Question {

    private int id;
    private String questionText;
    private Answer answer;

    public TextQuestion(){}

    public TextQuestion(int id, String questionText) {
        this.id = id;
        this.questionText = questionText;
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

    @Override
    public void clearAnswer() {
        this.answer = null;
    }

    @Override
    public void setScale(int scale) {

    }
}
