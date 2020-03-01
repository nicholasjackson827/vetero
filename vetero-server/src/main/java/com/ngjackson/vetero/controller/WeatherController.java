package com.ngjackson.vetero.controller;

import com.ngjackson.vetero.models.Location;
import com.ngjackson.vetero.models.User;
import com.ngjackson.vetero.models.WeatherLocation;
import com.ngjackson.vetero.models.WeatherResponse;
import com.ngjackson.vetero.repositories.UserRepository;
import com.ngjackson.vetero.services.WeatherService;
import com.ngjackson.vetero.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class WeatherController {

  @Autowired
  private UserRepository userRepository;

  /**
   * Gets weather for a user.
   *
   * @param userId User ID to get weather for.
   * @param forceUpdate True if you wish to get a fresh report for the user (default is false).
   * @return A WeatherResponse with the data for all of that users' locations.
   */
  @GetMapping("/weather/")
  public WeatherResponse getWeatherForUser(
      @RequestParam("userId") Long userId,
      @RequestParam(value = "forceUpdate", required = false, defaultValue = "false") boolean forceUpdate
  ) throws InterruptedException, IOException, URISyntaxException {

    // First, get the user and their zip codes
    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, userId));

    List<String> userZipCodes = user.getLocations().stream()
        .map(Location::getZip)
        .collect(Collectors.toList());

    // Have the weather service get the weather for each zip code
    WeatherService weatherService = new WeatherService();
    List<WeatherLocation> weatherLocations = weatherService.getWeather(userZipCodes, forceUpdate);

    // Get the last time each field was updated and take the min (earliest) as the last time it was updated
    ZonedDateTime lastUpdated = weatherLocations.stream()
        .map(WeatherLocation::getLastUpdated)
        .min(Comparator.naturalOrder())
        .orElse(null);

    // Return a new response with the above data
    return new WeatherResponse(user.getUsername(), weatherLocations, lastUpdated);
  }

}
