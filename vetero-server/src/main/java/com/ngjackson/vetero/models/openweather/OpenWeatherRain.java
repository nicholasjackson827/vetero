package com.ngjackson.vetero.models.openweather;

import com.google.gson.annotations.SerializedName;

public class OpenWeatherRain {

  @SerializedName("1h")
  private Double lastHour;

  public Double getLastHour() {
    return lastHour;
  }

  public void setLastHour(Double lastHour) {
    this.lastHour = lastHour;
  }
}
