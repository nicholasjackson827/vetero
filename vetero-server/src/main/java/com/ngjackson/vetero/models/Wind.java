package com.ngjackson.vetero.models;

import com.ngjackson.vetero.utils.WeatherUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Wind {

  private Double speed;
  private String direction;

  public Double getSpeed() {
    return speed;
  }

  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public void setDirection(Long degrees) {
    this.direction = WeatherUtil.getDirectionFromDegrees(degrees);
  }

}
