package com.example.employeeManagement.repository;

import com.example.employeeManagement.model.Employees;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository <Employees, String> {
}
