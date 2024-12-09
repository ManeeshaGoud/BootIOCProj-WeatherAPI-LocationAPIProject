package testing;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class WeatherServiceTest {

    @Test
    void testGetWeather() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        WeatherService weatherService = new WeatherService(restTemplate);

        // Mock response from Weather API
        WeatherService.Weather weather = new WeatherService.Weather(25.0, 50, "clear sky");
        when(restTemplate.getForObject(anyString(), eq(WeatherService.WeatherResponse.class)))
                .thenReturn(weather);

        WeatherService.Weather result = weatherService.getWeather("Pune");
        assertNotNull(result);
        assertEquals(25.0, result.getTemperature());
    }
}


