package man.survey;

import org.springframework.social.twitter.api.*;
import java.util.List;

public class EmbedQuestionAdapterTwitter implements EmbedQuestionAdapter {

    public Twitter twitter;

    public EmbedQuestionAdapterTwitter(Twitter twitter){
        this.twitter = twitter;
    }

    @Override
    public String getEmbedLink() {

        String trend = getRandomTrend();
        Tweet tweet = getRandomTweetFromHashtag(trend);

        return getOembedLink(tweet);
    }

    public String getRandomTrend(){
        List<Trend> hashtags = twitter
                .searchOperations()
                .getLocalTrends(23424975, false) //UK's "where-on-earth" (WOE) ID   //true excludes hashtagged trends
                .getTrends();

        String randomHashtag = hashtags.get((int) (Math.random() * 10)).getName();

        return randomHashtag;
    }

    public Tweet getRandomTweetFromHashtag(String hashtag) {
        List<Tweet> tweetList = twitter.searchOperations().search(
                new SearchParameters(hashtag)
                        .lang("en")
                        .resultType(SearchParameters.ResultType.POPULAR)
                        .count(1)
                        .includeEntities(true)
        ).getTweets();

        return tweetList.get(0);
    }

    public String getOembedLink(Tweet tweet){

        OEmbedTweet oembed = twitter.timelineOperations().getStatusOEmbed(tweet.getIdStr(),
                new OEmbedOptions()
                        .maxWidth(500)
                        .align("center")
                        .language("en"));

        return oembed.getHtml();
    }

}
