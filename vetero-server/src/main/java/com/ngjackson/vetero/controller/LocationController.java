package com.ngjackson.vetero.controller;

import com.ngjackson.vetero.models.CreateLocationRequest;
import com.ngjackson.vetero.models.DeleteLocationRequest;
import com.ngjackson.vetero.models.Location;
import com.ngjackson.vetero.models.User;
import com.ngjackson.vetero.repositories.LocationRepository;
import com.ngjackson.vetero.repositories.UserRepository;
import com.ngjackson.vetero.services.OpenWeatherService;
import com.ngjackson.vetero.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LocationController {

  private static final String VALID_NUMBER_REGEX = "\\d+(\\.\\d+)?";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LocationRepository locationRepository;

  /**
   * Get all locations for a user.
   *
   * @param userId User ID to get locations for.
   * @return A Set of locations that belong to that user.
   */
  @GetMapping("/locations/")
  public Set<Location> getLocations(@RequestParam("userId") Long userId) {
    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, userId));
    return user.getLocations();
  }

  /**
   * Create a location.
   *
   * @param request A completed CreateLocationRequest with user ID and zip code.
   * @return A location (if successful)
   */
  @PostMapping("/locations/")
  public Location createLocation(@RequestBody CreateLocationRequest request) {

    // Quickly validate the zip code
    String zip = request.getZip();
    if (zip == null || zip.length() != 5 || !zip.matches(VALID_NUMBER_REGEX)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Invalid zip format!"
      );
    }

    // Have the weather service quickly check if it can find data for that zip
    try {
      if (!OpenWeatherService.isKnownZip(zip)) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Unable to find city with zip!"
        );
      }
    } catch (InterruptedException | IOException | URISyntaxException e) {
      e.printStackTrace();
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR,
          "Unknown error while parsing zip!"
      );
    }

    // The request must have a zip, so go ahead and create the location (if it doesn't exist)
    Location location = new Location();
    location.setZip(zip);
    location = locationRepository.save(location);

    // If the request also has a user ID (meaning the location is associated with a user)
    // get that user and associate it with the location
    Long userId = request.getUserId();
    if (userId == null) {
      return location;
    }

    // Get the user so we can save the location to it
    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, userId));

    user.getLocations().add(location);
    userRepository.save(user);

    return location;
  }

  /**
   * Delete a location for a user. It doesn't really delete the location, just
   * disassociates it from the user.
   *
   * @param request The delete location request with user ID and zip code.
   * @return Empty response with 204 if successful.
   */
  @DeleteMapping("/locations/")
  public ResponseEntity deleteLocation(@RequestBody DeleteLocationRequest request) {

    // Get the user to remove the location from (if they exist)
    Long userId = request.getUserId();
    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, userId));

    // Get the locations to keep by taking the existing locations, minus the one with the requested zip
    Set<Location> locationsToKeep = user.getLocations()
        .stream()
        .filter(l -> !l.getZip().equals(request.getZip()))
        .collect(Collectors.toSet());

    // Persist
    user.setLocations(locationsToKeep);
    userRepository.save(user);

    // Return no content (even if we tried to remove a location that didn't exist)
    return new ResponseEntity(HttpStatus.NO_CONTENT);

  }

}
