package com.ngjackson.vetero.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Wind {

  private static final int DEGREES_IN_CIRCLE = 360;

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
    this.direction = getDirectionFromDegrees(degrees);
  }

  /**
   * Get a direction (N, E, NE, etc.) from a degree (180, 56, etc.)
   *
   * @param degrees A number of degrees
   * @return The direction from the degrees, one of the 8 cardinal directions
   */
  private static String getDirectionFromDegrees(long degrees) {

    // Create a list of all possible directions (just going with the 8 basics for now)
    ArrayList<String> directions = new ArrayList<>(Arrays.asList("N NE E SE S SW W NW".split(" ")));
    long totalDivisions = directions.size();
    double divisionSize = DEGREES_IN_CIRCLE / totalDivisions;

    // We need to convert the degrees into an index
    // The easiest way to do this (I think) is the following formula:
    int index = (int) Math.ceil((degrees - (divisionSize / 2)) / divisionSize);

    // This works because we want to first "offset" the range of valid degrees by half the division
    // so that 90 degrees will be due east. Anything "east" should be between 90 +/- 22.5.
    // Then, to know which "chunk" to choose, we take the ceiling of that value to get an index.
    return directions.get(index);
  }

}
