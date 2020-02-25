package com.ngjackson.vetero.services;

import com.ngjackson.vetero.models.Location;
import com.ngjackson.vetero.models.WeatherLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {

  private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

  private HttpClient client;

  public WeatherService() {
    this.client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(20))
        .build();
  }

  public List<WeatherLocation> getWeather(List<String> zipCodes) throws InterruptedException, IOException, URISyntaxException {
    // TODO: Make this call asynchronous
    List<WeatherLocation> list = new ArrayList<>();
    for (String zipCode : zipCodes) {
      WeatherLocation weather = getWeather(zipCode);
      list.add(weather);
    }
    return list;
  }

  public WeatherLocation getWeather(String zipCode) throws URISyntaxException, IOException, InterruptedException {
    String url = BASE_URL + "?zip=" + zipCode + ",us&units=imperial&appid=" + SecretsService.getOpenWeatherApiKey();
    HttpRequest request = HttpRequest.newBuilder(new URI(url)).build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return WeatherLocation.deserializeFromJson(zipCode, response.body());
  }

}
