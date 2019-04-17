package com.udevise.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataMismatchException extends ValidationException {

  private static String message = "We're having trouble fulfilling you're request.";

  public DataMismatchException(String message) {
    super(message);
  }

  public DataMismatchException() {
    super(message);
  }

  public DataMismatchException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataMismatchException(Throwable cause) {
    super(cause);
  }
}
