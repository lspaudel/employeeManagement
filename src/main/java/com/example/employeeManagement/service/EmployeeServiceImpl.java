package com.example.employeeManagement.service;

import com.example.employeeManagement.exception.EmployeeNotFoundException;
import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employees createEmployee(Employees employees) {

        return employeeRepository.save(employees);
    }


    @Override
    public Employees getEmployeeById(String id) {
        return employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee ID not found"));
    }

    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);

    }
}
