package com.example.employeeManagement.service;

import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.repository.EmployeeRepository;

import java.util.List;

public interface EmployeeService {

    Employees createEmployee(Employees employees);
    List<Employees> getAllEmployees();
    Employees getEmployeeById(String id);
    String deleteEmployee(String id);
    Employees updateEmployee(Employees existing);
}
