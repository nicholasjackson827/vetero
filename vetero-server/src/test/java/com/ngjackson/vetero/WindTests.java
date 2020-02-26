package com.ngjackson.vetero;

import com.ngjackson.vetero.utils.WeatherUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class WindTests {

  @Test
  public void testDegreesToDirection() {

    assertEquals("N", WeatherUtil.getDirectionFromDegrees(0));
    assertEquals("N", WeatherUtil.getDirectionFromDegrees(15));
    assertEquals("NE", WeatherUtil.getDirectionFromDegrees(30));
    assertEquals("NE", WeatherUtil.getDirectionFromDegrees(45));
    assertEquals("NE", WeatherUtil.getDirectionFromDegrees(60));
    assertEquals("E", WeatherUtil.getDirectionFromDegrees(75));
    assertEquals("E", WeatherUtil.getDirectionFromDegrees(90));
    assertEquals("E", WeatherUtil.getDirectionFromDegrees(105));
    assertEquals("SE", WeatherUtil.getDirectionFromDegrees(120));
    assertEquals("SE", WeatherUtil.getDirectionFromDegrees(135));
    assertEquals("SE", WeatherUtil.getDirectionFromDegrees(150));
    assertEquals("S", WeatherUtil.getDirectionFromDegrees(165));
    assertEquals("S", WeatherUtil.getDirectionFromDegrees(180));
    assertEquals("S", WeatherUtil.getDirectionFromDegrees(195));
    assertEquals("SW", WeatherUtil.getDirectionFromDegrees(210));
    assertEquals("SW", WeatherUtil.getDirectionFromDegrees(225));
    assertEquals("SW", WeatherUtil.getDirectionFromDegrees(240));
    assertEquals("W", WeatherUtil.getDirectionFromDegrees(255));
    assertEquals("W", WeatherUtil.getDirectionFromDegrees(270));
    assertEquals("W", WeatherUtil.getDirectionFromDegrees(285));
    assertEquals("NW", WeatherUtil.getDirectionFromDegrees(300));
    assertEquals("NW", WeatherUtil.getDirectionFromDegrees(315));
    assertEquals("NW", WeatherUtil.getDirectionFromDegrees(330));
    assertEquals("N", WeatherUtil.getDirectionFromDegrees(345));

  }

}
