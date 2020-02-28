package com.ngjackson.vetero.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;
import java.util.List;

public class WeatherResponse {

  private String username;
  private List<WeatherLocation> locations;

  @JsonFormat(pattern = "MM/dd hh:mm:ss a", timezone = "US/Eastern")
  private ZonedDateTime lastUpdated;

  public WeatherResponse() {
    // Empty constructor
  }

  public WeatherResponse(String username, List<WeatherLocation> locations, ZonedDateTime lastUpdated) {
    this.username = username;
    this.locations = locations;
    this.lastUpdated = lastUpdated;
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

  public ZonedDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(ZonedDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
