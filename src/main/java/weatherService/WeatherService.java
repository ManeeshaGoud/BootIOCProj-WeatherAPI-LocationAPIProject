package weatherService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "weather", key = "#city", unless = "#result == null")
    public Weather getWeather(String city) {
        String url = String.format(weatherApiUrl, city, apiKey);
        try {
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            if (response != null) {
                return new Weather(response.getMain().getTemp(), response.getMain().getHumidity(), response.getWeather().get(0).getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve weather data.");
        }
        return null;
    }

    public static class Weather {
        private double temperature;
        private int humidity;
        private String description;

        // Constructor, getters, and setters
    }

    // WeatherResponse would be a DTO representing the API response.
}

