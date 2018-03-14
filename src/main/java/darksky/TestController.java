package darksky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.plogitech.darksky.forecast.DarkSkyClient;
import tk.plogitech.darksky.forecast.model.Forecast;

@Controller
public class TestController {

    DarkSkyApiService darkSkyApiService;

    @Autowired
    public TestController(DarkSkyApiService darkSkyApiService){this.darkSkyApiService=darkSkyApiService;}


    @RequestMapping("/")
    public String getForecast(){
        return darkSkyApiService.getForecast(53.4808,2.2426).getCurrently().getPrecipIntensity().toString();
    }
}
