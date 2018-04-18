package survey;

import java.util.List;

public class Section {

    private int id;
    private List<Question> questions;

    public Section(int id, List<Question> questions) {
        this.id = id;
        this.questions = questions;
    }

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

}
