package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherByIpService weatherByIpService;

    public WeatherController(WeatherByIpService weatherByIpService) {
        this.weatherByIpService = weatherByIpService;
    }

    @GetMapping("/weather-by-ip")
    public WeatherByIpService.WeatherResponse getWeatherByIp(@RequestParam(required = false) String ip) {
        // If IP is not provided, use the client's IP from the request
        if (ip == null) {
            ip = getClientIp();
        }
        return weatherByIpService.getWeatherByIp(ip);
    }

    private String getClientIp() {
        // Logic to extract the client's IP address from the request
        // This could be from the HTTP request headers (e.g., X-Forwarded-For)
        return "192.168.1.1"; // Placeholder
    }
}




