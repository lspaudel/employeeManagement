package com.example.employeeManagement.service;

import com.example.employeeManagement.exception.DatabaseConnectionException;
import com.example.employeeManagement.exception.DuplicateEmployeeIdException;
import com.example.employeeManagement.exception.EmployeeNotFoundException;
import com.example.employeeManagement.exception.InvalidIdFormatException;
import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employees createEmployee(Employees employees) {
        try {
            employeeRepository.save(employees);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Database error while saving employee", e);
        }
        return employeeRepository.save(employees);
    }

    @Override
    public Employees getEmployeeById(String id) {
        if (id == null || id.isBlank()) {
            throw new InvalidIdFormatException("Employee id cannot be empty");
        }
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found: " + id));
    }

    @Override
    public List<Employees> getAllEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new DatabaseConnectionException("unable to fetch employee", e);
        }
    }

    @Override
    public Employees updateEmployee(String id, Employees updatedEmployee) {
        if (id == null || id.isBlank()){
            throw new InvalidIdFormatException("Employee id cannot be empty");
        }
        Employees existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found: " + id));

        if (!id.equals(updatedEmployee.getId()) && employeeRepository.existsById(updatedEmployee.getId())){
            throw new DuplicateEmployeeIdException("Employee ID already exists: " + updatedEmployee.getId());
        }

        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setLastName(updatedEmployee.getLastName());
        existing.setAge(updatedEmployee.getAge());
        existing.setId(updatedEmployee.getId());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(String id) {
        if (id == null || id.isBlank()) {
            throw new InvalidIdFormatException("Employee ID cannot be empty");
        }

        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
