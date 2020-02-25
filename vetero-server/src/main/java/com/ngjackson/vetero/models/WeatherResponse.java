package com.ngjackson.vetero.models;

import java.util.List;

public class WeatherResponse {

  private String username;
  private List<WeatherLocation> locations;

  public WeatherResponse() {
    // Empty constructor
  }

  public WeatherResponse(String username, List<WeatherLocation> locations) {
    this.username = username;
    this.locations = locations;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<WeatherLocation> getLocations() {
    return locations;
  }

  public void setLocations(List<WeatherLocation> locations) {
    this.locations = locations;
  }

}
