package com.example.employeeManagement.service;

import com.example.employeeManagement.exception.*;
import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    //    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employees createEmployee(Employees employees) {
        log.info("Creating employee: firstName={}, email={}",
                employees.getFirstName(), employees.getEmail());

        // Check ID conflict if user provides an ID
        if (employees.getId() != null && employeeRepository.existsById(employees.getId())) {
            log.warn("ID conflict for id={}", employees.getId());
            throw new ConflictException("Employee ID already exists: " + employees.getId());
        }
        // Check email conflict
        boolean emailExists = employeeRepository.countByEmailIgnoreCase(employees.getEmail())>0;
        if (emailExists) {
            log.warn("Email conflict for email={}", employees.getEmail());
            throw new ConflictException("Employee email already exists: " + employees.getEmail());
        }

        Employees saved = employeeRepository.save(employees);
        log.info("Employee created with id={}", saved.getId());
        return saved;
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
    public Page<Employees> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employees updateEmployee(Employees existing) {
        log.info("Updating employee with id = {}", existing.getId());
        try {
            return employeeRepository.save(existing);
        } catch (Exception e) {
            log.error("Error updating employee with id = {}", existing.getId(), e);
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
