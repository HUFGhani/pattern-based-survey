package survey;

import java.util.List;

public class Survey {

    private List<Section> sections;

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
}
