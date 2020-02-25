package com.ngjackson.vetero.services;

public class SecretsService {

  private static final String OPENWEATHER_API_KEY_ENV_NAME = "OPENWEATHER_API_KEY";

  public static String getOpenWeatherApiKey() {
    return System.getenv(OPENWEATHER_API_KEY_ENV_NAME);
  }

}
