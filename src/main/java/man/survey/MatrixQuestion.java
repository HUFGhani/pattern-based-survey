package man.survey;

import java.util.ArrayList;
import java.util.List;

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

    public void setScale(int scale) {
        this.scale = scale;
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

    @Override
    public List<Question> getQuestions() {
        List<Question> list = new ArrayList<Question>();
        list.add(this);

        return list;
    }


}
