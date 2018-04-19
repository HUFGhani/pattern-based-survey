package man.survey;

import man.darksky.TwitterApiService;
import org.springframework.social.twitter.api.*;

public class EmbedQuestionAdapterTwitter implements EmbedQuestionAdapter {

    public TwitterApiService twitterApiService;

    public EmbedQuestionAdapterTwitter(TwitterApiService twitterApi){
        this.twitterApiService = twitterApi;
    }

    @Override
    public String getEmbedLink() {

        String trend = twitterApiService.getRandomTrend();
        Tweet tweet = twitterApiService.getRandomTweetFromHashtag(trend);

        return twitterApiService.getOembedLink(tweet);
    }

}
