package com.ngjackson.vetero.utils;

import com.google.gson.Gson;
import com.ngjackson.vetero.models.WeatherLocation;
import com.ngjackson.vetero.models.Wind;
import com.ngjackson.vetero.models.openweather.OpenWeatherApiResponse;
import com.ngjackson.vetero.models.openweather.OpenWeatherWeather;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WeatherUtil {

  private static final int DEGREES_IN_CIRCLE = 360;
  private static final double INCH_MERCURY_PER_HECTOPASCAL = 0.02953;

  /**
   * Get a direction (N, E, NE, etc.) from a degree (180, 56, etc.)
   *
   * @param degrees A number of degrees
   * @return The direction from the degrees, one of the 8 cardinal directions
   */
  public static String getDirectionFromDegrees(long degrees) {

    // Create a list of all possible directions (just going with the 8 basics for now)
    ArrayList<String> directions = new ArrayList<>(Arrays.asList("N NE E SE S SW W NW N".split(" ")));
    long totalDivisions = directions.size() - 1;
    double divisionSize = DEGREES_IN_CIRCLE / totalDivisions;

    // We need to convert the degrees into an index
    // The easiest way to do this (I think) is the following formula:
    int index = (int) Math.ceil((degrees - (divisionSize / 2)) / divisionSize);

    // This works because we want to first "offset" the range of valid degrees by half the division
    // so that 90 degrees will be due east. Anything "east" should be between 90 +/- 22.5.
    // Then, to know which "chunk" to choose, we take the ceiling of that value to get an index.
    return directions.get(index);
  }


  public static WeatherLocation deserializeFromJson(String zipCode, String json) {
    WeatherLocation location = new WeatherLocation();
    Gson gson = new Gson();

    OpenWeatherApiResponse response = gson.fromJson(json, OpenWeatherApiResponse.class);

    location.setZip(zipCode);
    location.setLocationName(response.getName());
    location.setTemp(response.getTemperatures().getTemp());
    location.setFeelsLike(response.getTemperatures().getFeelsLike());
    location.setPressure(convertHectopascalToInchOfMercury((double) response.getTemperatures().getPressure()));
    location.setHumidity(response.getTemperatures().getHumidity());
    location.setLastUpdated(ZonedDateTime.now());

    String weatherStatusDescription = response
        .getWeather()
        .stream()
        .map(OpenWeatherWeather::getMainName)
        .collect(Collectors.joining(", "));
    location.setDescription(weatherStatusDescription);

    Wind wind = new Wind();
    // The API sometimes likes to not give us wind direction, so null check this first
    if (response.getWind().getDeg() != null) {
      wind.setDirection(response.getWind().getDeg());
    } else {
      wind.setDirection("");
    }
    wind.setSpeed(response.getWind().getSpeed());
    location.setWind(wind);

    return location;
  }

  public static double convertHectopascalToInchOfMercury(Double hectopascal) {
    return hectopascal * INCH_MERCURY_PER_HECTOPASCAL;
  }

}
