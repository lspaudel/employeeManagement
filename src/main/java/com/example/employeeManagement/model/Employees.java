package com.example.employeeManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employees")
@Data
public class Employees {
    @Id
    private String id;

//    @Field("emp_name")
    private String firstName;
    private String lastName;
    private int age;
}
