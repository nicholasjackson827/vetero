package com.ngjackson.vetero.services;

import com.ngjackson.vetero.models.WeatherLocation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

  public WeatherService() {

  }

  public List<WeatherLocation> getWeather(List<String> zipCodes, boolean forceUpdate) throws InterruptedException, IOException, URISyntaxException {
    // TODO: Make this call asynchronous
    List<WeatherLocation> list = new ArrayList<>();
    for (String zipCode : zipCodes) {
      WeatherLocation weather = OpenWeatherService.getWeather(zipCode, forceUpdate);
      list.add(weather);
    }
    return list;
  }
}
