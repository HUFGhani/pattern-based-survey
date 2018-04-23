package savesurvery;

public class SurveryWriterCaretaker {
    private Object obj;

    public void save(SurveryWriterUtil fileWriter){
        this.obj=fileWriter.save();
    }

    public void undo(SurveryWriterUtil fileWriter){
        fileWriter.undoToLastSave(obj);
    }
}
