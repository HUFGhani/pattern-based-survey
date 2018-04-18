package man.survey;

public class Alternative {

    private int id;
    private String alternativeText;

    public Alternative(int id, String alternativeText) {
        this.id = id;
        this.alternativeText = alternativeText;
    }

    public Alternative() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlternativeText() {
        return alternativeText;
    }

    public void setAlternativeText(String alternativeText) {
        this.alternativeText = alternativeText;
    }
}
