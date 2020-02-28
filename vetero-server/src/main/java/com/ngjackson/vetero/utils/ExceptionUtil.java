package com.ngjackson.vetero.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionUtil {

  /**
   * Build a ResponseStatusException with a status code of NOT_FOUND and a custom message.
   *
   * @param clazz The class that wasn't found.
   * @param id The ID of the class that wasn't found.
   * @return A built ResponseStatusException, ready to be thrown.
   */
  public static ResponseStatusException buildNotFoundException(Class clazz, Long id) {
    return new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Could not find " + clazz.getSimpleName() + " with ID " + id
    );
  }
}
