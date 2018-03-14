package twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.Tweet;
import java.awt.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/svc/v1/tweets")
public class TwitterController {

    public static final String TWITTER_BASE_URI = "svc/v1/tweets";

    @Autowired
    private Twitter twitter;

    @RequestMapping(value = "{hashTag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List getTweets(@PathVariable final String hashTag) {
        return (List) twitter.searchOperations().search(hashTag, 20).getTweets();
    }

}
