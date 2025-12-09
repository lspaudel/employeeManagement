package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeRequestDto;
import com.example.employeeManagement.dto.EmployeeResponseDto;
import com.example.employeeManagement.mapper.EmployeeMapper;
import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto) {
        Employees employee = employeeMapper.toModel(requestDto);
        Employees savedEmployee = employeeService.createEmployee(employee);
        EmployeeResponseDto responseDto = employeeMapper.toDto(savedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getEmployees(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        // If page or size is missing, return full list
        if (page == null || size == null) {
            List<EmployeeResponseDto> employees = employeeService.getAllEmployees()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
            return ResponseEntity.ok(employees);
        }

        // Otherwise, return paginated results
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeResponseDto> employeesPage = employeeService.getEmployees(pageable)
                .map(employeeMapper::toDto);

        return ResponseEntity.ok(employeesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable String id) {
        Employees employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeMapper.toDto(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        Employees existing = employeeService.getEmployeeById(id);
        employeeMapper.updateEntityFromDto(requestDto, existing);
        Employees updated = employeeService.updateEmployee(existing);

        return ResponseEntity.ok(employeeMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String id) {
        String message = employeeService.deleteEmployee(id);
        Map<String, String> response =  new HashMap<>();
        response.put("message",message);
        return ResponseEntity.ok(response);
    }
}
