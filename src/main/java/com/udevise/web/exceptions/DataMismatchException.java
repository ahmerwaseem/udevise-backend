package com.udevise.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataMismatchException extends ValidationException {

  public DataMismatchException(String message) {
    super(message);
  }

  public DataMismatchException() {
    super();
  }

  public DataMismatchException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataMismatchException(Throwable cause) {
    super(cause);
  }
}
