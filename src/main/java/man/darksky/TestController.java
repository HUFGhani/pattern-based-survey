package man.darksky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    DarkSkyApiAdapter darkSkyApiAdapter;

    double manchesterLat = 53.4640067;
    double manchesterLon = -2.2243379;

    @Autowired
    public TestController(DarkSkyApiAdapter darkSkyApiAdapter){this.darkSkyApiAdapter = darkSkyApiAdapter;}


    @RequestMapping("/")
    @ResponseBody
    public String getForecast(){
        return darkSkyApiAdapter.getForecast(manchesterLat, manchesterLon).getCurrently().getPrecipIntensity().toString();
    }

    @RequestMapping("/weather")
    @ResponseBody
    public String getWeatherTest(){

        List data = darkSkyApiAdapter.getForecast(manchesterLat, manchesterLon).getHourly().getData();

        String toString = "";

        for (Object item : data) {
            toString += item.toString();
        }
        //return toString;

        return darkSkyApiAdapter.getForecast(manchesterLat, manchesterLon).getHourly().getSummary();

    }

    @RequestMapping("/temps")
    @ResponseBody
    public String temperatureTest(){

        double tempRovaniemi = darkSkyApiAdapter.getForecast(66.2911788,53.5806892 ).getCurrently().getTemperature();
        double tempManchester = darkSkyApiAdapter.getForecast( manchesterLat, manchesterLon ).getCurrently().getTemperature();
        double tempDomingo = darkSkyApiAdapter.getForecast(12.0976239,-86.3985472).getCurrently().getTemperature();

        String timezone = darkSkyApiAdapter.getForecast(manchesterLat, manchesterLon ).getTimezone();
        String time = darkSkyApiAdapter.getForecast(manchesterLat, manchesterLon ).getCurrently().getTime().toString();

        return tempRovaniemi + " " + tempManchester + " " + tempDomingo + "<br>" + time + " " + timezone;
    }


}
