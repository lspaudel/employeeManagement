package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeRequestDto;
import com.example.employeeManagement.dto.EmployeeResponseDto;
import com.example.employeeManagement.mapper.EmployeeMapper;
import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto) {
        Employees employee = EmployeeMapper.toModel(requestDto);
        Employees savedEmployee = employeeService.createEmployee(employee);
        EmployeeResponseDto responseDto = EmployeeMapper.toDto(savedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployees()
                .stream()
                .map(EmployeeMapper::toDto)
                .toList();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable String id) {
        Employees employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(EmployeeMapper.toDto(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        Employees existing = employeeService.getEmployeeById(id);
        EmployeeMapper.updateEntityFromDto(requestDto, existing);
        Employees updated = employeeService.updateEmployee(existing);

        return ResponseEntity.ok(EmployeeMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String id) {
        String message = employeeService.deleteEmployee(id);
        Map<String, String> response =  new HashMap<>();
        response.put("message",message);
        return ResponseEntity.ok(response);
    }
}
