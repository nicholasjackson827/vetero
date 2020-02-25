package com.ngjackson.vetero.models.openweather;

import com.google.gson.annotations.SerializedName;

public class OpenWeatherWeather {

  private Long id;

  @SerializedName("main")
  private String mainName;

  private String description;
  private String icon;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMainName() {
    return mainName;
  }

  public void setMainName(String mainName) {
    this.mainName = mainName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}
