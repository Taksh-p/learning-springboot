package com.example.mappings.mappings.services;

import com.example.mappings.mappings.entities.DepartmentEntity;
import com.example.mappings.mappings.entities.EmployeeEntity;
import com.example.mappings.mappings.repositories.DepartmentRepository;
import com.example.mappings.mappings.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

       return departmentEntity.flatMap(department ->
            employeeEntity.map(employee -> {
              department.setManager(employee);
             return departmentRepository.save(department);
           })).orElse(null);
    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.setWorkerDepartment(department);
                    employeeRepository.save(employee);
                    department.getWorkers().add(employee);
                    return department;

                })).orElse(null);
    }

    public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.getFreelancersDepartment().add(department);
                    employeeRepository.save(employee);
                    department.getFreelancers().add(employee);
                    return department;
                })).orElse(null);
    }
}
