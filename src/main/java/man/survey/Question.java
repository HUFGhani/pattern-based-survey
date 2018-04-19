package man.survey;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = McQuestion.class, name = "McQuestion"),
        @JsonSubTypes.Type(value = MatrixQuestion.class, name = "MatrixQuestion"),
        @JsonSubTypes.Type(value = TextQuestion.class, name = "TextQuestion"),
        @JsonSubTypes.Type(value = EmbedQuestion.class, name = "EmbedQuestion")}
)
public abstract class Question {

    public abstract int getId();

    public abstract void setId(int id);

    public abstract String getQuestionText();

    public abstract void setQuestionText(String questionText);

    public abstract Answer getAnswer();

    public abstract void setAnswer(Answer answer);


}
