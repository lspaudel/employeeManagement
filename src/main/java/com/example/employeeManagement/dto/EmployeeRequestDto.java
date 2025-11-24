package com.example.employeeManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = false) // Throw an error when extra fields appear
public class EmployeeRequestDto {
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Min(value=18, message = "Age must be at least 18")
    @Max(value = 65, message = "Age must be at most 65")
    private int age;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = " Email should be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "phone number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Department cannot be empty")
    private String department;

    @Positive(message = "Salary must be positive")
    private double salary;

}
