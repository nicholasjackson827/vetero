package com.ngjackson.vetero.models.openweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenWeatherApiResponse {

  @SerializedName("coord")
  private OpenWeatherCoordinates coordinates;

  private List<OpenWeatherWeather> weather;
  private String base;

  @SerializedName("main")
  private OpenWeatherTemperatures temperatures;

  private Long visibility;
  private OpenWeatherWind wind;
  private OpenWeatherRain rain;
  private OpenWeatherClouds clouds;

  @SerializedName("dt")
  private Long dateTime;

  @SerializedName("sys")
  private OpenWeatherSystemInfo systemInfo;

  private Long timezone;

  @SerializedName("id")
  private Long openWeatherId;

  private String name;

  @SerializedName("cod")
  private Long code;

  public OpenWeatherCoordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(OpenWeatherCoordinates coordinates) {
    this.coordinates = coordinates;
  }

  public List<OpenWeatherWeather> getWeather() {
    return weather;
  }

  public void setWeather(List<OpenWeatherWeather> weather) {
    this.weather = weather;
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public OpenWeatherTemperatures getTemperatures() {
    return temperatures;
  }

  public void setTemperatures(OpenWeatherTemperatures temperatures) {
    this.temperatures = temperatures;
  }

  public Long getVisibility() {
    return visibility;
  }

  public void setVisibility(Long visibility) {
    this.visibility = visibility;
  }

  public OpenWeatherWind getWind() {
    return wind;
  }

  public void setWind(OpenWeatherWind wind) {
    this.wind = wind;
  }

  public OpenWeatherRain getRain() {
    return rain;
  }

  public void setRain(OpenWeatherRain rain) {
    this.rain = rain;
  }

  public OpenWeatherClouds getClouds() {
    return clouds;
  }

  public void setClouds(OpenWeatherClouds clouds) {
    this.clouds = clouds;
  }

  public Long getDateTime() {
    return dateTime;
  }

  public void setDateTime(Long dateTime) {
    this.dateTime = dateTime;
  }

  public OpenWeatherSystemInfo getSystemInfo() {
    return systemInfo;
  }

  public void setSystemInfo(OpenWeatherSystemInfo systemInfo) {
    this.systemInfo = systemInfo;
  }

  public Long getTimezone() {
    return timezone;
  }

  public void setTimezone(Long timezone) {
    this.timezone = timezone;
  }

  public Long getOpenWeatherId() {
    return openWeatherId;
  }

  public void setOpenWeatherId(Long openWeatherId) {
    this.openWeatherId = openWeatherId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }
}
