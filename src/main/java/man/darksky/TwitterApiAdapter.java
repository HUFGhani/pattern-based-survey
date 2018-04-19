package man.darksky;

import man.config.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterApiAdapter {

    ConfigurationProperties properties;

    @Autowired
    public TwitterApiAdapter(ConfigurationProperties properties) {
        this.properties = properties;
    }

//    @Bean
//    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//    public Twitter twitter(ConnectionRepository connectionRepository) {
//        Connection<Twitter> twitter = connectionRepository.findPrimaryConnection(Twitter.class);
//        return twitter != null ? twitter.getApi() : new TwitterTemplate(properties.getTwitterKey(), properties.getTwitterSecret());
//    }

    @Bean
    public Twitter getTwitterInstance() {
        return new TwitterTemplate(properties.getTwitterKey(), properties.getTwitterSecret());
    }

}
