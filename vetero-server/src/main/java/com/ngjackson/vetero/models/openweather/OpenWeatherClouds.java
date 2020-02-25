package com.ngjackson.vetero.models.openweather;

import com.google.gson.annotations.SerializedName;

public class OpenWeatherClouds {

  @SerializedName("all")
  private String cloudiness;

  public String getCloudiness() {
    return cloudiness;
  }

  public void setCloudiness(String cloudiness) {
    this.cloudiness = cloudiness;
  }
}
