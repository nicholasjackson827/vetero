package com.ngjackson.vetero.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

  @GetMapping("/test/")
  public String testEndpoint() {
    return "It works!";
  }

}
