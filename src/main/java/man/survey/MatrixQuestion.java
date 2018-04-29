package man.survey;

public class MatrixQuestion extends Question{

    int scale;
    private int id;
    private String questionText;
    private Answer answer;

    public MatrixQuestion(){}

    public MatrixQuestion(int id, int scale, String questionText) {
        this.scale = scale;
        this.id = id;
        this.questionText = questionText;
    }

    public int getScale() {
        return scale;
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
        this.scale = scale;
    }

}
