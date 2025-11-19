package com.example.employeeManagement.exception;

public class DuplicateEmployeeIdException extends RuntimeException {
    public DuplicateEmployeeIdException(String message) {
        super(message);
    }
}
