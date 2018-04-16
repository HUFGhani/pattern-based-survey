package darksky;

import javassist.compiler.ast.StringL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

//Call with ../get-tweets/%23some-hashtag
@RestController

@RequestMapping(TwitterController.TWITTER_BASE_URI)
public class TwitterController {

    public static final String TWITTER_BASE_URI = "get-tweets";

    @Autowired
    private Twitter twitter;

    @ResponseBody
    @RequestMapping(value = "{hashTag}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Tweet> getTweets(@PathVariable final String hashTag) {

        return twitter.searchOperations().search(hashTag, 10).getTweets();
    }

    @GetMapping("/list")
    public List<Tweet> list() {

        List<Tweet> list = twitter.searchOperations().search("trump", 10).getTweets();

        for (Tweet tweet : list) {
            tweet.getText();
        }
        return list;
    }

    @GetMapping("/text")
    public ArrayList<String> listString(){

        List<Tweet> list = twitter.searchOperations().search("trump", 10).getTweets();
        ArrayList<String> stringList = new ArrayList<String>();

        for (Tweet tweet : list) {
            stringList.add(tweet.getText());
        }

        return stringList;
    }

    @GetMapping("/popular-tweets")
    public List<Tweet> popularTweets() {

        SearchResults results = twitter.searchOperations().search(
                new SearchParameters("#manutd")
                        //.geoCode(new GeoCode(52.379241, 4.900846, 100, GeoCode.Unit.MILE))
                        .lang("en")
                        .resultType(SearchParameters.ResultType.POPULAR)
                        .count(10)
                        .includeEntities(false));

        List<Tweet> tweets = results.getTweets();

        return tweets;
    }

    @GetMapping("/popular-trends")
    public ArrayList<String> popularTrends() {

        List<Trend> trends = twitter
                .searchOperations()
                .getLocalTrends(23424975, false) //UK's "where-on-earth" (WOE) ID   //true excludes hashtagged trends
                .getTrends();

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(trends.get(i).getName());
        }

        return list;
    }

    @GetMapping("/random")
    @ResponseBody
    public String RandomTweet(){

        String hashtag = getRandomTrend();
        return getTweetFromHashtag(hashtag);
    }

    public String getRandomTrend(){
        List<Trend> hashtags = twitter
                .searchOperations()
                .getLocalTrends(23424975, false) //UK's "where-on-earth" (WOE) ID   //true excludes hashtagged trends
                .getTrends();

        String rndHashtag = hashtags.get((int) (Math.random() * 10)).getName();

        return rndHashtag;
    }

    public String getTweetFromHashtag(String hashtag) {
        Tweet tweet = twitter.searchOperations().search(
                new SearchParameters(hashtag)
                        //.geoCode(new GeoCode(52.379241, 4.900846, 100, GeoCode.Unit.MILE))
                        .lang("en")
                        .resultType(SearchParameters.ResultType.POPULAR)
                        .count(1)
                        .includeEntities(false))
                .getTweets().get(0);

        return tweet.getText();
    }
}
