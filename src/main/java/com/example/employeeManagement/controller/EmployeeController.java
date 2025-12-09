package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeRequestDto;
import com.example.employeeManagement.dto.EmployeeResponseDto;
import com.example.employeeManagement.mapper.EmployeeMapper;
import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Employee API", description = "Endpoints for managing employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Operation(
            summary = "Create a new employee",
            description = "Takes an EmployeeRequestDto object, validates the data, and saves a new employee record."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "409", description = "Email or ID already exists")
    })
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto) {
        Employees employee = employeeMapper.toModel(requestDto);
        Employees savedEmployee = employeeService.createEmployee(employee);
        EmployeeResponseDto responseDto = employeeMapper.toDto(savedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @Operation(
            summary = "Get all employees or paginated employees",
            description = """
                    If page and size parameters are not provided, this returns the full employee list.
                    If both parameters are provided, it returns a paginated employee result.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee list fetched successfully")
    })
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
    @Operation(
            summary = "Get employee by ID",
            description = "Fetches an employee record using the employee's unique ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee ID not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable String id) {
        Employees employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeMapper.toDto(employee));
    }
    @Operation(
            summary = "Update an existing employee",
            description = "Updates employee details using the provided ID and request body. Validates input fields."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Employee ID not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        Employees existing = employeeService.getEmployeeById(id);
        employeeMapper.updateEntityFromDto(requestDto, existing);
        Employees updated = employeeService.updateEmployee(existing);

        return ResponseEntity.ok(employeeMapper.toDto(updated));
    }
    @Operation(
            summary = "Delete an employee",
            description = "Deletes an employee by ID and returns a confirmation message."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee ID not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String id) {
        String message = employeeService.deleteEmployee(id);
        Map<String, String> response =  new HashMap<>();
        response.put("message",message);
        return ResponseEntity.ok(response);
    }
}
