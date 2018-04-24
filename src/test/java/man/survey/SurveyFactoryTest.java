package man.survey;

import man.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;

/**
 * Created by swsmi on 2018/4/23.
 */
@SpringBootTest(classes = App.class)
public class SurveyFactoryTest  extends AbstractTestNGSpringContextTests{
    @Autowired
    SurveyFactory surveyFactory;

    @Test
    public void testNotNullSurvey() throws Exception {
        Survey survey = surveyFactory.getSurvey("survey1");
        assertNotNull(survey);

        survey = surveyFactory.getSurvey("survey2");
        assertNotNull(survey);
    }

    @Test
    public void testTheSameSurvey() throws Exception {
        Survey survey1 = surveyFactory.getSurvey("survey1");

        Survey survey2 = surveyFactory.getSurvey("survey1");
        assertTrue(survey1 == survey2);
    }

    @Test
    public void testEmbedQuestion() throws Exception {
        Survey survey = surveyFactory.getSurvey("survey1");

        String emberdLinkStr;
        for (Section s : survey.getSections()) // Invokes adapter to obtain a link for embedding Tweet/Weather content
            for (Question q: s.getQuestions())
                if (q instanceof EmbedQuestion) {
                    emberdLinkStr = ((EmbedQuestion) q).getEmbedLink();
                    assertTrue(emberdLinkStr.startsWith("<iframe id=\"forecast_embed\"") || emberdLinkStr.startsWith("<blockquote class=\"twitter-tweet\""));
                }
    }





}