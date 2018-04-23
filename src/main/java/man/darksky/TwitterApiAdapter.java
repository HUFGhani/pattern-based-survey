package man.darksky;

import man.config.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterApiAdapter {

    ConfigurationProperties properties;

    @Autowired
    public TwitterApiAdapter(ConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Twitter getTwitterInstance() {
        return new TwitterTemplate(properties.getTwitterKey(), properties.getTwitterSecret());
    }

}
