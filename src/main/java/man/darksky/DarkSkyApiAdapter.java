package man.darksky;

import man.config.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;


@Service
public class DarkSkyApiAdapter {

    ConfigurationProperties configurationProperties;

    @Autowired
    public DarkSkyApiAdapter(ConfigurationProperties configurationProperties){this.configurationProperties = configurationProperties;}

    public Forecast getForecast(double lat, double lon){
        try {
            ForecastRequest request = new ForecastRequestBuilder()
                    .key(new APIKey(configurationProperties.getDarkSkyApiKey()))
                    .location(new GeoCoordinates(new Longitude(lon), new Latitude(lat)))
                    .units(ForecastRequestBuilder.Units.auto)
                    .language(ForecastRequestBuilder.Language.en)
                    .build();

            DarkSkyJacksonClient client = new DarkSkyJacksonClient();
            return client.forecast(request);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
