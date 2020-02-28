package com.ngjackson.vetero;

import com.ngjackson.vetero.models.WeatherLocation;
import com.ngjackson.vetero.utils.WeatherUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherUtilTest {

  @Test
  public void shouldReturnDirectionFromDegrees() {

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

  @Test
  public void parsingJsonShouldReturnPojo() {

    String zip = "12345";
    WeatherLocation location = WeatherUtil.deserializeFromJson(zip, TestUtils.VALID_WEATHER_JSON);

    assertEquals(zip, location.getZip());
    assertEquals((Double) 25.65, location.getTemp());
    assertEquals("Clouds", location.getDescription());
    assertEquals((Double) 18.34, location.getWind().getSpeed());

  }

  @Test
  public void shouldConvertHectopascalToInchOfMercury() {
    assertEquals((Double) 29.53000, (Double) WeatherUtil.convertHectopascalToInchOfMercury(1000.0));
    assertEquals((Double) 30.26825, (Double) WeatherUtil.convertHectopascalToInchOfMercury(1025.0));
    assertEquals((Double) 31.00650, (Double) WeatherUtil.convertHectopascalToInchOfMercury(1050.0));
    assertEquals((Double) 31.74475, (Double) WeatherUtil.convertHectopascalToInchOfMercury(1075.0));
    assertEquals((Double) 32.48300, (Double) WeatherUtil.convertHectopascalToInchOfMercury(1100.0));

    assertEquals((Double) 0.0, (Double) WeatherUtil.convertHectopascalToInchOfMercury(0.0));
  }

}
