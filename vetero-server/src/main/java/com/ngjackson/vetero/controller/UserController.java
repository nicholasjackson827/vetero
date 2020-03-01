package com.ngjackson.vetero.controller;

import com.ngjackson.vetero.models.User;
import com.ngjackson.vetero.repositories.UserRepository;
import com.ngjackson.vetero.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

  @Autowired
  private UserRepository userRepository;

  /**
   * Get a user by ID.
   *
   * @param id ID for the user.
   * @return The User object, if found.
   */
  @GetMapping("/users/{id}")
  public User getUser(@PathVariable("id") Long id) {
    return userRepository
      .findById(id)
      .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, id));
  }

  /**
   * Get a user by username.
   *
   * @param username ID for the user.
   * @return The User object, if found.
   */
  @GetMapping("/users/")
  public User getUserByUsername(@RequestParam("username") String username) {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "User not found!"
      );
    }
    return user;
  }

  /**
   * Create a user.
   *
   * @param newUser The new user object (minus the ID).
   * @return The newly created user, with ID, if created.
   */
  @PostMapping("/users/")
  public User createUser(@RequestBody User newUser) {

    // Check to make sure that username is unique
    if (userRepository.findByUsername(newUser.getUsername()) != null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Username already taken!"
      );
    }

    return userRepository.save(newUser);
  }

  /**
   * Delete a user.
   *
   * @param id ID of the user to delete.
   * @return Empty response, if successful.
   */
  @DeleteMapping("/users/{id}")
  public ResponseEntity deleteUser(@PathVariable("id") Long id) {
    try {
      userRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw ExceptionUtil.buildNotFoundException(User.class, id);
    }
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
