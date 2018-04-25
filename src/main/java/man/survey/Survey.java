package man.survey;

import java.util.List;

public class Survey {

    private String name;
    private List<Section> sections;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Survey(){}

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return sections;
    }

    public Section getSectionById(int id){
        for(Section section : sections){
            if(section.getId()==id){
                return section;
            }
        }
        return null;
    }

    public void setAnswer(int sectionID, int questionID, Answer answer){

        Section section = getSectionById(sectionID);
        section.getQuestions().get(questionID).setAnswer(answer);
    }
}
