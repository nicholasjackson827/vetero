package com.ngjackson.vetero.controller;

import com.ngjackson.vetero.models.User;
import com.ngjackson.vetero.repositories.UserRepository;
import com.ngjackson.vetero.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users/{id}")
  public User getUser(@PathVariable("id") Long id) {
    return userRepository
      .findById(id)
      .orElseThrow(() -> ExceptionUtil.buildNotFoundException(User.class, id));
  }

  @GetMapping("/users/")
  public User getUserByUsername(@RequestParam("username") String username) {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Unable to find user with username " + username + "!"
      );
    }
    return user;
  }

  @PostMapping("/users/")
  public User createUser(@RequestBody User newUser) {

    // Check to make sure that username is unique
    if (userRepository.findByUsername(newUser.getUsername()) != null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "User with username \"" + newUser.getUsername() + "\" already exists!"
      );
    }

    return userRepository.save(newUser);
  }

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
