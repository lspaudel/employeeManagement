package com.example.employeeManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
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
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError body = new ApiError(
                "Validatation Failed",
                "VALIDATION_ERROR",
                LocalDateTime.now(),
                details
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private HttpStatus mapErrorCodeToStatus(String errorCode){
        return switch (errorCode){
            case "NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "INVALID_REQUEST" -> HttpStatus.OK;
            case "CONFLICT" -> HttpStatus.CONFLICT;
            case "DATABASE_ERROR" -> HttpStatus.SERVICE_UNAVAILABLE;
            case "VALIDATION_ERROR" -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.BAD_REQUEST;
        };
    }
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException ex) {
        HttpStatus status = mapErrorCodeToStatus(ex.getErrorCode());
        ApiError body = new ApiError(
                ex.getMessage(),
                ex.getErrorCode(),
                LocalDateTime.now(),
                null
        );
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex){
        ApiError body = new ApiError(
                "An unexpected error occured",
                "INTERNAL_ERROR",
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
