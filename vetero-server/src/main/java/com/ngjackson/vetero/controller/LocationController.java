package com.ngjackson.vetero.controller;

import com.ngjackson.vetero.models.CreateLocationRequest;
import com.ngjackson.vetero.models.Location;
import com.ngjackson.vetero.models.User;
import com.ngjackson.vetero.repositories.LocationRepository;
import com.ngjackson.vetero.repositories.UserRepository;
import com.ngjackson.vetero.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class LocationController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LocationRepository locationRepository;

  @GetMapping("/locations/")
  public Set<Location> getLocations(@RequestParam("userId") Long userId) {
    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, userId));
    return user.getLocations();
  }

  @PostMapping("/locations/")
  public Location createLocation(@RequestBody CreateLocationRequest request) {

    // The request must have a zip, so go ahead and create the location (if it doesn't exist)
    Location location = new Location();
    location.setZip(request.getZip());
    location = locationRepository.save(location);

    // If the request also has a user ID (meaning the location is associated with a user)
    // get that user and associate it with the location
    Long userId = request.getUserId();
    if (userId == null) {
      return location;
    }

    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, userId));

    user.getLocations().add(location);
    userRepository.save(user);

    return location;
  }

}
