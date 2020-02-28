package com.ngjackson.vetero.utils;

import com.google.gson.Gson;
import com.ngjackson.vetero.models.WeatherLocation;
import com.ngjackson.vetero.models.Wind;
import com.ngjackson.vetero.models.openweather.OpenWeatherApiResponse;
import com.ngjackson.vetero.models.openweather.OpenWeatherWeather;

import java.math.BigDecimal;
import java.math.MathContext;
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

  /**
   * Deserialize a response from the OpenWeather API into a WeatherLocation POJO.
   *
   * @param zipCode The zip code for the POJO.
   * @param json The JSON of to be deserialized.
   * @return A completed WeatherLocation object, if successful.
   */
  public static WeatherLocation deserializeFromJson(String zipCode, String json) {
    WeatherLocation location = new WeatherLocation();
    Gson gson = new Gson();

    // Have GSON do all the heavy lifting for us
    OpenWeatherApiResponse response = gson.fromJson(json, OpenWeatherApiResponse.class);

    // Map the OpenWeather objects to the WeatherLocation object.
    location.setZip(zipCode);
    location.setLocationName(response.getName());
    location.setTemp(response.getTemperatures().getTemp());
    location.setFeelsLike(response.getTemperatures().getFeelsLike());
    location.setPressure(convertHectopascalToInchOfMercury((double) response.getTemperatures().getPressure()));
    location.setHumidity(response.getTemperatures().getHumidity());
    location.setLastUpdated(ZonedDateTime.now());

    // The API often returns multiple weather descriptions, so join them by commas
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

  /**
   * Convert hectopascals to inches of mercury.
   *
   * @param hectopascal The qty of hectopascals to convert.
   * @return The same qty in inches of mercury, rounded to 5 decimal places.
   */
  public static double convertHectopascalToInchOfMercury(Double hectopascal) {
    // Round this to 5 decimal places to knock off the end of the wonderful floating point math
    return new BigDecimal(hectopascal * INCH_MERCURY_PER_HECTOPASCAL).round(new MathContext(7)).doubleValue();
  }

}
