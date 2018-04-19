package man.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
@Service
public class ConfigurationProperties {

    public ConfigurationProperties(){}

    @Value("${api.key.darksky}")
    String darkSkyApiKey;

    @Value("${survey.folder}")
    String surveyFolderName;

    @Value("${survey.default.name}")
    String defaultSurveyName;

    @Value("${spring.social.twitter.app-id}")
    String twitterKey;

    @Value("${spring.social.twitter.app-secret}")
    String twitterSecret;

    public String getDefaultSurveyName() {return defaultSurveyName;}

    public String getDarkSkyApiKey() {return darkSkyApiKey;}

    public String getSurveyFolder() {return surveyFolderName;}

    public String getTwitterKey() {return twitterKey;}

    public String getTwitterSecret() {return twitterSecret;}

}
