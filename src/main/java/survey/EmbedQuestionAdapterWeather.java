package survey;

import darksky.DarkSkyApiService;

public class EmbedQuestionAdapterWeather implements EmbedQuestionAdapter {


    DarkSkyApiService darkSkyApiService;

    double lat = 53.4640067;
    double lon = -2.2243379;
    String displayName = "Manchester";

    public EmbedQuestionAdapterWeather(DarkSkyApiService darkSkyApiService) {
        this.darkSkyApiService = darkSkyApiService;
    }

    @Override
    public String getEmbedLink() {

        //TODO: change this to include darksky Api
        //System.out.println(darkSkyApiService.getForecast(lat, lon).getCurrently().getTemperature() + "");

        String embedWeather = "<iframe id=\"forecast_embed\" frameborder=\"0\" height=\"245\" width=\"60%\"\n" +
                "            src=\"//forecast.io/embed/#lat=" + lat + "&amp;lon=" + lon +
                "&amp;name=" + displayName + "&amp;units=uk&amp;color=#00aaff\"></iframe>";

        return embedWeather;
    }
}
