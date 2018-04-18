package darksky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterApiService {

    @Autowired
    private Twitter twitter;

    public String getRandomTrend(){
        List<Trend> hashtags = twitter
                .searchOperations()
                .getLocalTrends(23424975, false) //UK's "where-on-earth" (WOE) ID   //true excludes hashtagged trends
                .getTrends();

        String rndHashtag = hashtags.get((int) (Math.random() * 10)).getName();

        return rndHashtag;
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
                        //.hideMedia()
                        //.hideThread()
                        //.omitScript()
                        .align("center")
                        .language("en"));

        return oembed.getHtml();
    }


}
