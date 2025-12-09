package com.example.employeeManagement.mapper;

import com.example.employeeManagement.dto.EmployeeRequestDto;
import com.example.employeeManagement.dto.EmployeeResponseDto;
import com.example.employeeManagement.model.Employees;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employees toModel(EmployeeRequestDto employeeRequestDto){
        Employees employees = new Employees();
        employees.setFirstName(employeeRequestDto.getFirstName());
        employees.setLastName(employeeRequestDto.getLastName());
        employees.setAge(employeeRequestDto.getAge());
        employees.setEmail(employeeRequestDto.getEmail());
        employees.setPhoneNumber(employeeRequestDto.getPhoneNumber());
        employees.setDepartment(employeeRequestDto.getDepartment());
        employees.setSalary(employeeRequestDto.getSalary());

        return employees;

    }

    public EmployeeResponseDto toDto(Employees employees){
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
//        employeeResponseDto.setId(employees.getId());
        employeeResponseDto.setFirstName(employees.getFirstName());
        employeeResponseDto.setLastName(employees.getLastName());
//        employeeResponseDto.setAge(employees.getAge());
        employeeResponseDto.setEmail(employees.getEmail());
//        employeeResponseDto.setPhoneNumber(employees.getPhoneNumber());
        employeeResponseDto.setDepartment(employees.getDepartment());
//        employeeResponseDto.setSalary(employees.getSalary());
        return employeeResponseDto;
    }
    public void updateEntityFromDto(EmployeeRequestDto dto, Employees existing) {
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setAge(dto.getAge());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setDepartment(dto.getDepartment());
        existing.setSalary(dto.getSalary());
        // Do NOT set ID
    }
}
