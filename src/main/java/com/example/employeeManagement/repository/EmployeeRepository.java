package com.example.employeeManagement.repository;

import com.example.employeeManagement.model.Employees;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EmployeeRepository extends MongoRepository <Employees, String> {
//    @Query(value = "{ 'email': { $regex: ?0, $options: 'i' } }") // case-insensitive
    boolean existsByEmail(String email);
}
