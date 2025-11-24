package com.example.employeeManagement.mapper;

import com.example.employeeManagement.dto.EmployeeRequestDto;
import com.example.employeeManagement.dto.EmployeeResponseDto;
import com.example.employeeManagement.model.Employees;

public class EmployeeMapper {

    public static Employees toModel(EmployeeRequestDto employeeRequestDto){
        Employees employees = new Employees();
        employees.setFirstName(employeeRequestDto.getFirstName());
        employees.setLastName(employeeRequestDto.getFirstName());
        employees.setAge(employeeRequestDto.getAge());
        return employees;

    }

    public static EmployeeResponseDto toDto(Employees employees){
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(employees.getId());
        employeeResponseDto.setFirstName(employees.getFirstName());
        employeeResponseDto.setLastName(employees.getLastName());
        employeeResponseDto.setAge(employees.getAge());
        return employeeResponseDto;
    }
}
