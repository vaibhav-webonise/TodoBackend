package com.webonise.todoapp.Exception;

public class TokenNotValidException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TokenNotValidException(String message) {
    super(message);
  }
}
