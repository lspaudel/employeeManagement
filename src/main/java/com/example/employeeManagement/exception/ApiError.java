package com.example.employeeManagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
    private List<String> details;


}
