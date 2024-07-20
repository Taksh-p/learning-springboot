package com.example.apis.apilearning.controller;

import com.example.apis.apilearning.dto.EmployeeDto;
import com.example.apis.apilearning.entities.EmployeeEntity;
import com.example.apis.apilearning.exceptions.ResourcesNotFound;
import com.example.apis.apilearning.repositories.EmployeeRepository;
import com.example.apis.apilearning.services.EmployeeServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {


    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable( name = "employeeId") Long id){
        Optional<EmployeeDto> employeeDto =  employeeServices.getEmployeeById(id);

        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElseThrow(() -> new ResourcesNotFound("Employee not found"));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        return ResponseEntity.ok(employeeServices.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody @Valid EmployeeDto inputEmployee){
        EmployeeDto employeeDto =  employeeServices.createNewEmployee(inputEmployee);

        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody @Valid EmployeeDto updateValue, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeServices.updateEmployeeById(employeeId, updateValue));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeBuId(@PathVariable Long employeeId){


       boolean gotDeleted =  employeeServices.deleteEmployeeBuId(employeeId);

       if(gotDeleted) return  ResponseEntity.ok(true);

       return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> partiallyUpdateTheEmployeeDetails(@PathVariable Long employeeId, @RequestBody Map<String, Object> fieldToBeUpdated){
        return ResponseEntity.ok(employeeServices.partiallyUpdateTheEmployeeDetails(employeeId, fieldToBeUpdated));
    }

}
