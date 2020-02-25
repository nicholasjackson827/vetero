package com.ngjackson.vetero.controller;

import com.ngjackson.vetero.models.Location;
import com.ngjackson.vetero.models.User;
import com.ngjackson.vetero.models.WeatherLocation;
import com.ngjackson.vetero.models.WeatherResponse;
import com.ngjackson.vetero.repositories.UserRepository;
import com.ngjackson.vetero.services.WeatherService;
import com.ngjackson.vetero.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WeatherController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/weather/")
  public WeatherResponse getWeatherForUser(
      @RequestParam("userId") Long userId,
      @RequestParam(value = "forceUpdate", required = false, defaultValue = "false") boolean forceUpdate
  ) throws InterruptedException, IOException, URISyntaxException {
    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, userId));

    List<String> userZipCodes = user.getLocations().stream()
        .map(Location::getZip)
        .collect(Collectors.toList());

    WeatherService weatherService = new WeatherService();
    List<WeatherLocation> weatherLocations = weatherService.getWeather(userZipCodes, forceUpdate);

    return new WeatherResponse(user.getUsername(), weatherLocations);
  }

}
