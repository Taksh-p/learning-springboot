package com.example.mappings.mappings.controller;

import com.example.mappings.mappings.entities.DepartmentEntity;
import com.example.mappings.mappings.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity department){
        return departmentService.createNewDepartment(department);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId){

        return departmentService.assignManagerToDepartment(departmentId, employeeId);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(
            @PathVariable Long departmentId,
            @PathVariable Long employeeId){

        return departmentService.assignWorkerToDepartment(departmentId, employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(
            @PathVariable Long departmentId,
            @PathVariable Long employeeId){

        return departmentService.assignFreelancerToDepartment(departmentId, employeeId);
    }
}
