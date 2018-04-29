package man.survey;

import java.util.List;

public class Section implements QuestionFormatter {

    private int id;
    private List<Question> questions;

    public Section(int id, List<Question> questions) {
        this.id = id;
        this.questions = questions;
    }

    public Section() {}

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestionById(int id){
        for(Question question : questions){
            if(question.getId()==id){
                return question;
            }
        }
        return null;
    }

    @Override
    public void clearAnswer() {
        questions.forEach(q->q.clearAnswer());
    }

    @Override
    public void setScale(int scale) {
        questions.forEach(q->q.setScale(scale));
    }
}
