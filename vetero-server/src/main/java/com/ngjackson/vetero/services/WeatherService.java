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
    // Empty constructor
  }

  /**
   * Get weather for a list of zip codes.
   *
   * @param zipCodes List of zip codes to get weather for.
   * @param forceUpdate True if we should force an update for these zips.
   * @return A list of WeatherLocations if successful.
   */
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
