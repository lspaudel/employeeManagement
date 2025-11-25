package com.example.employeeManagement.exception;

public class InvalidRequestException extends BaseException {
    public InvalidRequestException(String message) {
        super(message, "INVALID_REQUEST");

    }
}
