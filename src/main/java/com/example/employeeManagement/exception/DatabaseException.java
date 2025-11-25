package com.example.employeeManagement.exception;

public class DatabaseException extends BaseException {
    public DatabaseException(String message) {
        super(message, "DATABASE_ERROR");
    }
}
