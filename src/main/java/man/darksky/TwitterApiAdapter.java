package man.darksky;

import man.config.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public Twitter getTwitterInstance() {
        return new TwitterTemplate(properties.getTwitterKey(), properties.getTwitterSecret());
    }

    public String getRandomTrend(){
        List<Trend> hashtags = getTwitterInstance()
                .searchOperations()
                .getLocalTrends(23424975, false) //UK's "where-on-earth" (WOE) ID   //true excludes hashtagged trends
                .getTrends();

        String randomHashtag = hashtags.get((int) (Math.random() * 10)).getName();

        return randomHashtag;
    }

    public Tweet getRandomTweetFromHashtag(String hashtag) {
        List<Tweet> tweetList = getTwitterInstance().searchOperations().search(
                new SearchParameters(hashtag)
                        .lang("en")
                        .resultType(SearchParameters.ResultType.POPULAR)
                        .count(1)
                        .includeEntities(true)
        ).getTweets();

        return tweetList.get(0);
    }

    public String getOembedLink(Tweet tweet){

        OEmbedTweet oembed = getTwitterInstance().timelineOperations().getStatusOEmbed(tweet.getIdStr(),
                new OEmbedOptions()
                        .maxWidth(500)
                        .align("center")
                        .language("en"));

        return oembed.getHtml();
    }

}
