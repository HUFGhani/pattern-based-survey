package demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TwitterFeedController {

    @GetMapping("/twitter-feed")
    public String twitterFeed() {

        return "feedTemplate";
    }
}
