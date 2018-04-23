package savesurvery;

public class Memento {
    private String fileName;
    private StringBuilder content;

    public Memento(String file, StringBuilder content){
        this.fileName=file;
        //notice the deep copy so that Memento and FileWriterUtil content variables don't refer to same object
        this.content=new StringBuilder(content);
    }

    public String getFileName() {
        return fileName;
    }

    public Memento setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public StringBuilder getContent() {
        return content;
    }

    public Memento setContent(StringBuilder content) {
        this.content = content;
        return this;
    }
}
