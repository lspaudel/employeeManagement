package com.example.employeeManagement.service;

import com.example.employeeManagement.exception.*;
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

        // Check ID conflict if user provides an ID
        if (employees.getId() != null && employeeRepository.existsById(employees.getId())) {
            throw new ConflictException("Employee ID already exists: " + employees.getId());
        }
        // Check email conflict
        boolean emailExists = employeeRepository.countByEmailIgnoreCase(employees.getEmail())>0;
        if (emailExists) {
            throw new ConflictException("Employee email already exists: " + employees.getEmail());
        }
        return employeeRepository.save(employees);
    }

    @Override
    public Employees getEmployeeById(String id) {
        validateId(id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee ID not found: " + id));
    }

    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employees updateEmployee(Employees existing) {
        try {
            return employeeRepository.save(existing);
        } catch (Exception e) {
            throw new DatabaseException("Database error while updating employee");
        }
    }

    @Override
    public String deleteEmployee(String id) {
        validateId(id);

        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found: " + id);
        }
        employeeRepository.deleteById(id);
        return "Employee with ID " + id + " has been successfully deleted.";
    }

    // -----------------------
    // Helper method
    // -----------------------

    private void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new InvalidRequestException("Employee id cannot be empty");
        }
    }
}
