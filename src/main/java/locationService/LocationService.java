package locationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    @Value("${location.api.url}")
    private String locationApiUrl;
    
    @Value("${location.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public LocationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Location getLocation(String ip) {
        String url = String.format(locationApiUrl, ip, apiKey);
        try {
            LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);
            if (response != null) {
                return new Location(response.getCity(), response.getCountry());
            }
        } catch (Exception e) {
            // Log error for debugging
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve location data.");
        }
        return null;
    }

    public static class Location {
        private String city;
        private String country;

        // Constructor, getters, and setters
    }

    // LocationResponse would be a DTO representing the API response.
}




