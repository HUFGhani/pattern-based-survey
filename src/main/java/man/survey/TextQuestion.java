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

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText=questionText;
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}
