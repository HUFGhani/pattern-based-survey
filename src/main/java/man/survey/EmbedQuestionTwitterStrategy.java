package man.survey;

import man.darksky.TwitterApiAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

@Service
public class EmbedQuestionTwitterStrategy implements EmbedQuestionStrategy {

    @Autowired
    private TwitterApiAdapter twitterApiAdapter;

    public EmbedQuestionTwitterStrategy(TwitterApiAdapter twitterApiAdapter){
        this.twitterApiAdapter = twitterApiAdapter;
    }

    @Override
    public String getEmbedLink() {

        String trend = twitterApiAdapter.getRandomTrend();
        Tweet tweet = twitterApiAdapter.getRandomTweetFromHashtag(trend);

        return twitterApiAdapter.getOembedLink(tweet);
    }

}
