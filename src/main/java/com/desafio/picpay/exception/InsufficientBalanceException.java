package com.desafio.picpay.exception;

public class InsufficientBalanceException extends Exception {

  public InsufficientBalanceException() {
    super("Insufficient balance to complete the operation.");
  }

  public InsufficientBalanceException(String message) {
    super(message);
  }

  public InsufficientBalanceException(String message, Throwable cause) {
    super(message, cause);
  }

  public InsufficientBalanceException(Throwable cause) {
    super(cause);
  }
}
