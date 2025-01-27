package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;


@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private final String APIKEY = "a4d2bd3cb9d572b0b7c336d6d9f1d3b0";
    private HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();


    public Location findLocationByName(String name) throws URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/weather" +
                        "?q=" + name +
                        "&appid=" + APIKEY))
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), new TypeReference<List<LocationSearchDTO>>() {
            });
        } catch (IOException | InterruptedException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
