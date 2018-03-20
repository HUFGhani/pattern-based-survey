package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TwitterFeedController {

    String consumerKey = "t91pumPTu0khIGHqVnX2LbEde"; // The application's consumer key
    String consumerSecret = "UHB8jDL9YlU4v1uA3H1n4MxgG8iPHTt3DHDK90qzU8cuNTxPqU"; // The application's consumer secret
    String accessToken = "781795963874738176-qo8NNhYiq5pJpl87ts90Xsuod0Y1iPm"; // The access token granted after OAuth authorization
    String accessTokenSecret = "uW40W9XHElKd1YCQFTSHvN5yEYXd2BTiE6uAwFFWzZYs7"; // The access token secret granted after OAuth authorization
    Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);

    @GetMapping("/twitter-feed")
    public String twitterFeed() {

        return "feedTemplate";
    }

//    @Autowired
//    private Twitter twitter;

//    @GetMapping("/feed-home")
//    public List<Tweet> homeFeed() {
//        return twitter.timelineOperations().getHomeTimeline();
//
//    }
}
