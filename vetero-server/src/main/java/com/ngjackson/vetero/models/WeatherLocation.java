package com.ngjackson.vetero.models;

import com.google.gson.Gson;
import com.ngjackson.vetero.models.openweather.OpenWeatherApiResponse;
import com.ngjackson.vetero.models.openweather.OpenWeatherWeather;

import java.util.stream.Collectors;

public class WeatherLocation {

  private String zip;
  private String locationName;
  private String description;
  private Double temp;
  private Double feelsLike;
  private Long pressure;
  private Long humidity;
  private Wind wind;

  public static WeatherLocation deserializeFromJson(String zipCode, String json) {
    WeatherLocation location = new WeatherLocation();
    Gson gson = new Gson();

    OpenWeatherApiResponse response = gson.fromJson(json, OpenWeatherApiResponse.class);

    location.setZip(zipCode);
    location.setLocationName(response.getName());
    location.setTemp(response.getTemperatures().getTemp());
    location.setFeelsLike(response.getTemperatures().getFeelsLike());
    location.setPressure(response.getTemperatures().getPressure());
    location.setHumidity(response.getTemperatures().getHumidity());

    String weatherStatusDescription = response
        .getWeather()
        .stream()
        .map(OpenWeatherWeather::getMainName)
        .collect(Collectors.joining(", "));
    location.setDescription(weatherStatusDescription);

    Wind wind = new Wind();
    wind.setDirection(response.getWind().getDeg());
    wind.setSpeed(response.getWind().getSpeed());
    location.setWind(wind);

    return location;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getTemp() {
    return temp;
  }

  public void setTemp(Double temp) {
    this.temp = temp;
  }

  public Double getFeelsLike() {
    return feelsLike;
  }

  public void setFeelsLike(Double feelsLike) {
    this.feelsLike = feelsLike;
  }

  public Long getPressure() {
    return pressure;
  }

  public void setPressure(Long pressure) {
    this.pressure = pressure;
  }

  public Long getHumidity() {
    return humidity;
  }

  public void setHumidity(Long humidity) {
    this.humidity = humidity;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }
}
