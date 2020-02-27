package com.ngjackson.vetero.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SecretsService {

  private static final String OPENWEATHER_API_KEY_ENV_NAME = "OPENWEATHER_API_KEY";
  private static final String OPENWEATHER_API_KEY_FILE_PATH = "/run/secrets/openweather_api_key.txt";

  public static String getOpenWeatherApiKey() {

    // There are two places we check for the API key, either as an env variable or a file
    // Priority is the env variable, if it exists

    String key = System.getenv(OPENWEATHER_API_KEY_ENV_NAME);
    if (key != null) {
      return key;
    }

    try {
      key = Files.readAllLines(Paths.get(OPENWEATHER_API_KEY_FILE_PATH)).get(0);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return key;
  }

}
