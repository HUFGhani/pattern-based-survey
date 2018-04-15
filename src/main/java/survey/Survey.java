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
}
