package com.ngjackson.vetero.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionUtil {

  public static ResponseStatusException buildNotFoundException(Class clazz, Long id) {
    return new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Could not find " + clazz.getSimpleName() + " with ID " + id
    );
  }
}
