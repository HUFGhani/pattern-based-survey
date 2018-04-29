package man.survey;

import java.util.List;

public class Section {

    private int id;
    private Question question;

    public Section(int id, Question question) {
        this.id = id;
        this.question = question;
    }

    public Section() {}

    public Question getQuestions() {
        return question;
    }

    public void setQuestions(Question question) {
        this.question = question;
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
