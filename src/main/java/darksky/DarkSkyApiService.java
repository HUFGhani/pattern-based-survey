package darksky;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;


@Service
public class DarkSkyApiService {

    @Value("${api.key.darksky}")
    String apiKey;

    public DarkSkyApiService(){}

    public Forecast getForecast(double lat, double lon){
        try {
            ForecastRequest request = new ForecastRequestBuilder()
                    .key(new APIKey(apiKey))
                    .location(new GeoCoordinates(new Longitude(lat), new Latitude(lon)))
                    .units(ForecastRequestBuilder.Units.us)
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
