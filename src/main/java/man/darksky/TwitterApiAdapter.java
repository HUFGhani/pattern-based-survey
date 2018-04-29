package man.darksky;

import org.springframework.social.twitter.api.*;

public interface TwitterApiAdapter {

    String getRandomTrend();

    Tweet getRandomTweetFromHashtag(String hashtag);

    String getOembedLink(Tweet tweet);
}
