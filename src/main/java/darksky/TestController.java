package darksky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.plogitech.darksky.forecast.DarkSkyClient;
import tk.plogitech.darksky.forecast.model.Forecast;
import java.util.List;

@Controller
public class TestController {

    DarkSkyApiService darkSkyApiService;

    double manchesterLat = 53.4640067;
    double manchesterLon = -2.2243379;

    @Autowired
    public TestController(DarkSkyApiService darkSkyApiService){this.darkSkyApiService=darkSkyApiService;}


    @RequestMapping("/")
    @ResponseBody
    public String getForecast(){
        return darkSkyApiService.getForecast(manchesterLat, manchesterLon).getCurrently().getPrecipIntensity().toString();
    }

    @RequestMapping("/weather")
    @ResponseBody
    public String getWeatherTest(){

        List data = darkSkyApiService.getForecast(manchesterLat, manchesterLon).getHourly().getData();

        String toString = "";

        for (Object item : data) {
            toString += item.toString();
        }
        //return toString;

        return darkSkyApiService.getForecast(manchesterLat, manchesterLon).getHourly().getSummary();

    }

    @RequestMapping("/temps")
    @ResponseBody
    public String temperatureTest(){

        double tempRovaniemi = darkSkyApiService.getForecast(66.2911788,53.5806892 ).getCurrently().getTemperature();
        double tempManchester = darkSkyApiService.getForecast( manchesterLat, manchesterLon ).getCurrently().getTemperature();
        double tempDomingo = darkSkyApiService.getForecast(12.0976239,-86.3985472).getCurrently().getTemperature();

        String timezone = darkSkyApiService.getForecast(manchesterLat, manchesterLon ).getTimezone();
        String time = darkSkyApiService.getForecast(manchesterLat, manchesterLon ).getCurrently().getTime().toString();

        return tempRovaniemi + " " + tempManchester + " " + tempDomingo + "<br>" + time + " " + timezone;
    }
}
