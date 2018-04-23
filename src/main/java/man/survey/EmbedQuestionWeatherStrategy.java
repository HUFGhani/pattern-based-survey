package man.survey;

import man.darksky.DarkSkyApiAdapter;

public class EmbedQuestionWeatherStrategy implements EmbedQuestionStrategy {

    DarkSkyApiAdapter darkSkyApiAdapter;

    double lat = 53.4640067;
    double lon = -2.2243379;
    String displayName = "Manchester";

    public EmbedQuestionWeatherStrategy(DarkSkyApiAdapter darkSkyApiService) {
        this.darkSkyApiAdapter = darkSkyApiService;
    }

    @Override
    public String getEmbedLink() {

        //TODO: change this to include darksky Api
        //darkSkyApiAdapter.getForecast(lat, lon).get

        String embedWeather = "<iframe id=\"forecast_embed\" frameborder=\"0\" height=\"245\" width=\"60%\"\n" +
                "            src=\"//forecast.io/embed/#lat=" + lat + "&amp;lon=" + lon +
                "&amp;name=" + displayName + "&amp;units=uk&amp;color=#00aaff\"></iframe>";

        return embedWeather;
    }
}
