package com.example.employeeManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = false) // Throw an error when extra fields appear
public class EmployeeRequestDto {
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Min(value=14, message = "Age must be greater than 14")
    private int age;
}
