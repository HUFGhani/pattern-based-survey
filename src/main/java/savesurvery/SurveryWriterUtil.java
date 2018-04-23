package savesurvery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SurveryWriterUtil {
    private String fileName;
    private StringBuilder content;

    public SurveryWriterUtil(String file){
        this.fileName=file;
        this.content=new StringBuilder();
    }

    @Override
    public String toString(){
        return this.content.toString();
    }

    public void write(String str){
        content.append(str);
    }

    public Memento save(){
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(this.fileName);
            bw = new BufferedWriter(fw);
            bw.write(this.content.toString());

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }


        return new Memento(this.fileName,this.content);
    }

    public void undoToLastSave(Object obj){
        Memento memento = (Memento) obj;
        this.fileName  = memento.getFileName();
        this.content = memento.getContent();
    }
}
