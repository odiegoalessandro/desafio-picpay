package com.desafio.picpay.exception;

public class InvalidUserTypeException extends Exception {
  public InvalidUserTypeException() {
    super("Operation not allowed for this type of user.");
  }

  public InvalidUserTypeException(String message) {
    super(message);
  }
}

