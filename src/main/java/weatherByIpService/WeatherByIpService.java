package weatherByIpService;

import org.springframework.stereotype.Service;

@Service
public class WeatherByIpService {

    private final LocationService locationService;
    private final WeatherService weatherService;

    public WeatherByIpService(LocationService locationService, WeatherService weatherService) {
        this.locationService = locationService;
        this.weatherService = weatherService;
    }

    public WeatherResponse getWeatherByIp(String ip) {
        Location location = locationService.getLocation(ip);
        if (location == null) {
            throw new RuntimeException("Location not found.");
        }

        Weather weather = weatherService.getWeather(location.getCity());
        if (weather == null) {
            throw new RuntimeException("Weather data not available.");
        }

        return new WeatherResponse(ip, location, weather);
    }

    public static class WeatherResponse {
        private String ip;
        private Location location;
        private Weather weather;

        // Constructor, getters, and setters
    }
}




