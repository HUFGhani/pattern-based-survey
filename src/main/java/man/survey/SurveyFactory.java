package man.survey;


import com.fasterxml.jackson.databind.ObjectMapper;
import man.config.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public class SurveyFactory {

    ConfigurationProperties configurationProperties;

    public SurveyFactory(ConfigurationProperties configurationProperties){
        this.configurationProperties = configurationProperties;
        surveys = new HashMap<>();
    }

    private Map<String,Survey> surveys;

    public Survey getSurvey(String surveyName){
        Survey survey = surveys.get(surveyName);

        if(survey==null){
            survey = getSurveyFromFile(surveyName);
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
