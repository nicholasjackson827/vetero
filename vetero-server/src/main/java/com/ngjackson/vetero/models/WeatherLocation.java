package com.ngjackson.vetero.models;

import com.google.gson.Gson;
import com.ngjackson.vetero.models.openweather.OpenWeatherApiResponse;
import com.ngjackson.vetero.models.openweather.OpenWeatherWeather;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

public class WeatherLocation {

  private String zip;
  private String locationName;
  private String description;
  private Double temp;
  private Double feelsLike;
  private Double pressure;
  private Long humidity;
  private Wind wind;

  private transient ZonedDateTime lastUpdated;

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

  public Double getPressure() {
    return pressure;
  }

  public void setPressure(Double pressure) {
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

  public ZonedDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(ZonedDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
