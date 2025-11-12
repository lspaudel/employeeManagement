package com.example.employeeManagement.service;

import com.example.employeeManagement.exception.EmployeeNotFoundException;
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
        return employeeRepository.save(employees);
    }

    @Override
    public Employees getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found: " + id));
    }

    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employees updateEmployee(String id, Employees updatedEmployee) {
        Employees existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found: " + id));

        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setLastName(updatedEmployee.getLastName());
        existing.setAge(updatedEmployee.getAge());
        existing.setId(updatedEmployee.getId());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee ID not found: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
