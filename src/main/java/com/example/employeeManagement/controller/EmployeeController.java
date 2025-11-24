package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeRequestDto;
import com.example.employeeManagement.dto.EmployeeResponseDto;
import com.example.employeeManagement.mapper.EmployeeMapper;
import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDto createEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        Employees employees = EmployeeMapper.toModel(employeeRequestDto);
        Employees saved = employeeService.createEmployee(employees);
        System.out.println("DTO endpoint hit");
        return EmployeeMapper.toDto(saved);
    }

    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees()
                .stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable String id) {
        Employees employees = employeeService.getEmployeeById(id);
        return EmployeeMapper.toDto(employees);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponseDto updateEmployee(@PathVariable String id, @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        Employees employees =  EmployeeMapper.toModel(employeeRequestDto);
        Employees updated = employeeService.updateEmployee(id, employees);
        return EmployeeMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }
}
