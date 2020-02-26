package com.ngjackson.vetero.services;

import com.ngjackson.vetero.config.CacheConfig;
import com.ngjackson.vetero.models.WeatherLocation;
import com.ngjackson.vetero.models.openweather.OpenWeatherApiResponse;
import com.ngjackson.vetero.utils.WeatherUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class OpenWeatherService {

  private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
  private static final String API_KEY = SecretsService.getOpenWeatherApiKey();

  private static HttpClient client;
  private static CacheConfig cache = new CacheConfig();

  private static void configureClient() {
    client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(20))
        .build();
  }

  public static WeatherLocation getWeather(String zipCode, boolean forceUpdate) throws URISyntaxException, IOException, InterruptedException {

    // If a cached value is fine AND the cache has a value for this zip, use it!
    if (!forceUpdate && cache.getWeatherLocationCache().containsKey(zipCode)) {
      return cache.getWeatherLocationCache().get(zipCode);
    }

    configureClient();

    // Build the URL to hit the web service
    String url = BASE_URL + "?zip=" + zipCode + ",us&units=imperial&appid=" + API_KEY;
    HttpRequest request = HttpRequest.newBuilder(new URI(url)).build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
    WeatherLocation weatherLocation = WeatherUtil.deserializeFromJson(zipCode, response.body());

    // Store the value in the cache for future use
    cache.getWeatherLocationCache().put(zipCode, weatherLocation);

    return weatherLocation;
  }

  public static boolean isKnownZip(String zipCode) throws URISyntaxException, IOException, InterruptedException {
    if (cache.getWeatherLocationCache().containsKey(zipCode)) {
      return true;
    }

    String url = BASE_URL + "?zip=" + zipCode + ",us&units=imperial&appid=" + API_KEY;
    HttpRequest request = HttpRequest.newBuilder(new URI(url)).build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());

    if (response.statusCode() != HttpStatus.OK.value()) {
      return false;
    }

    // Since we have the data, might as well parse it and throw it in the cache
    WeatherLocation weatherLocation = WeatherUtil.deserializeFromJson(zipCode, response.body());
    cache.getWeatherLocationCache().put(zipCode, weatherLocation);

    return true;
  }

}
