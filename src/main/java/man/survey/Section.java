package man.survey;

import java.util.List;

public class Section {

    private int id;
    private Question questions;

    public Section(int id, Question question) {
        this.id = id;
        this.questions = question;
    }

    public Section() {}

    public List<Question> getQ() {
        return questions.getQuestions();
    }

    public void setQuestions(Question question) {
        this.questions = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Question getQuestionById(int id){
//        for(Question question : questions){
//            if(question.getId()==id){
//                return question;
//            }
//        }
//        return null;
//    }

}
