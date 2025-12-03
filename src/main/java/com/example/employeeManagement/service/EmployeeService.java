package com.example.employeeManagement.service;

import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employees createEmployee(Employees employees);
    List<Employees> getAllEmployees();
    Page<Employees> getEmployees(Pageable pageable);
    Employees getEmployeeById(String id);
    String deleteEmployee(String id);
    Employees updateEmployee(Employees existing);
}
