package man.survey;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = McQuestion.class, name = "McQuestion"),
        @JsonSubTypes.Type(value = MatrixQuestion.class, name = "MatrixQuestion"),
        @JsonSubTypes.Type(value = TextQuestion.class, name = "TextQuestion"),
        @JsonSubTypes.Type(value = EmbedQuestion.class, name = "EmbedQuestion"),
        @JsonSubTypes.Type(value = CompositeQuestion.class, name = "CompositeQuestion")}
)
public abstract class Question {

    public abstract int getId();

    public abstract void setId(int id);

    public abstract List<Question> getQuestions();
//    public abstract String getQuestionText();

//    public abstract void setQuestionText(String questionText);

    public abstract Answer getAnswer();

    public abstract void setAnswer(Answer answer);


}
