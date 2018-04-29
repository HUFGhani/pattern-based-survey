package man.survey;


import com.fasterxml.jackson.databind.ObjectMapper;
import man.config.ConfigurationProperties;
import man.darksky.DarkSkyApiAdapter;
import man.darksky.TwitterApiAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public class SurveyFactory {

    ConfigurationProperties configurationProperties;

    @Autowired
    private DarkSkyApiAdapter darkSkyApiAdapter;

    @Autowired
    private TwitterApiAdapter twitterApiAdapter;

    public SurveyFactory(ConfigurationProperties configurationProperties){
        this.configurationProperties = configurationProperties;
        surveys = new HashMap<>();
    }

    private Map<String,Survey> surveys;

    public Survey getSurvey(String surveyName){
        Survey survey = surveys.get(surveyName);

        if(survey==null){
            survey = getSurveyFromFile(surveyName);

            EmbedQuestionStrategy strategy = Math.random() < 0.5 ?
                    new EmbedQuestionTwitterStrategy(twitterApiAdapter) : new EmbedQuestionWeatherStrategy(darkSkyApiAdapter); // Get random embedded content
            for (Section s : survey.getSections()) // Invokes adapter to obtain a link for embedding Tweet/Weather content
                for (Question q: s.getQuestions())
                    if (q instanceof EmbedQuestion)
                        ((EmbedQuestion) q).setEmbedLink(strategy.getEmbedLink());

            surveys.put(surveyName,survey);
        }
        return survey;
    }


    private Survey getSurveyFromFile(String surveyName){
        Survey survey = null;
        String surveyPath = configurationProperties.getSurveyFolder() + "/" + surveyName + ".json";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            survey = objectMapper.readValue(new ClassPathResource(surveyPath).getFile(), Survey.class);
            return survey;
        } catch (Exception e){System.out.println(e);}

        return survey;
    }

}
