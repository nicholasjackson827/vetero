package com.ngjackson.vetero.models.openweather;

import com.google.gson.annotations.SerializedName;

public class OpenWeatherTemperatures {

  private Double temp;
  private Long pressure;
  private Long humidity;

  @SerializedName("feels_like")
  private Double feelsLike;

  @SerializedName("temp_min")
  private Double tempMin;

  @SerializedName("temp_max")
  private Double tempMax;

  public Double getTemp() {
    return temp;
  }

  public void setTemp(Double temp) {
    this.temp = temp;
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

  public Double getFeelsLike() {
    return feelsLike;
  }

  public void setFeelsLike(Double feelsLike) {
    this.feelsLike = feelsLike;
  }

  public Double getTempMin() {
    return tempMin;
  }

  public void setTempMin(Double tempMin) {
    this.tempMin = tempMin;
  }

  public Double getTempMax() {
    return tempMax;
  }

  public void setTempMax(Double tempMax) {
    this.tempMax = tempMax;
  }
}
