package com.example.employeeManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleValidationErrors(MethodArgumentNotValidException ex){
//        return ex.getBindingResult()
//                .getFieldError()
//                .getDefaultMessage();
//    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
    }


    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidIdFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidId(InvalidIdFormatException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicateEmployeeIdException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicate(DuplicateEmployeeIdException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleDb(DatabaseConnectionException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidEmployeeDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidData(InvalidEmployeeDataException ex) {
        return ex.getMessage();
    }
}
