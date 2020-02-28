package com.ngjackson.vetero;

public class TestUtils {

  public static final String VALID_WEATHER_JSON = "{\"coord\":{\"lon\":-73.94,\"lat\":42.81},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":25.65,\"feels_like\":9.84,\"temp_min\":21,\"temp_max\":28.99,\"pressure\":1002,\"humidity\":62},\"visibility\":16093,\"wind\":{\"speed\":18.34,\"deg\":280,\"gust\":28.86},\"clouds\":{\"all\":90},\"dt\":1582859038,\"sys\":{\"type\":1,\"id\":5137,\"country\":\"US\",\"sunrise\":1582803349,\"sunset\":1582843299},\"timezone\":-18000,\"id\":0,\"name\":\"Schenectady\",\"cod\":200}\n";


  public static String getBaseUrl(int port) {
    return "http://localhost:" + port + "/api";
  }

}
