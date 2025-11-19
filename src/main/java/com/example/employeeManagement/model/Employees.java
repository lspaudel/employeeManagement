package com.example.employeeManagement.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employees")
@Data
public class Employees {
    @Id
    @NotBlank(message = "id cannot be blank")
    private String id;

//    @Field("emp_name")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @NotBlank(message ="Last name cannot be empty")
    private String lastName;
    @Min(value = 14, message = "Employee should be at least 14 years of age")
    private int age;
}
