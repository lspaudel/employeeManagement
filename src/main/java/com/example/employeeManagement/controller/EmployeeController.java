package com.example.employeeManagement.controller;

import com.example.employeeManagement.model.Employees;
import com.example.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Employees createEmployee(@RequestBody Employees employees){
        return employeeService.createEmployee(employees);
    }
    @GetMapping("/fetch/all")
    public List<Employees> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/fetch/{id}")
    public Employees getEmployeeById(@PathVariable("id") String id ){
        return employeeService.getEmployeeById(id);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employees updateEmployee(@RequestBody Employees employees){
        return employeeService.createEmployee(employees);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void  deleteEmployee(@PathVariable String id ){
        employeeService.deleteEmployee(id);
    }
}
