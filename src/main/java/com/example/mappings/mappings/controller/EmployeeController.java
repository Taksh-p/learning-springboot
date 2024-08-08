package com.example.mappings.mappings.controller;

import com.example.mappings.mappings.entities.DepartmentEntity;
import com.example.mappings.mappings.entities.EmployeeEntity;
import com.example.mappings.mappings.repositories.EmployeeRepository;
import com.example.mappings.mappings.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{departmentId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.createNewEmployee(employeeEntity);
    }



}
