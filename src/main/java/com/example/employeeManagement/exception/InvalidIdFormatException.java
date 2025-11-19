package com.example.employeeManagement.exception;

public class InvalidIdFormatException extends RuntimeException {
  public InvalidIdFormatException(String message) {
    super(message);
  }
}
