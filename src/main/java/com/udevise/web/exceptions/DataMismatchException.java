package com.udevise.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataMismatchException extends RuntimeException {
  public DataMismatchException(String message) { super(message); }
}
