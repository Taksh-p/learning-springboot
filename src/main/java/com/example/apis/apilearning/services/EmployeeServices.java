package com.example.apis.apilearning.services;

import com.example.apis.apilearning.dto.EmployeeDto;
import com.example.apis.apilearning.entities.EmployeeEntity;
import com.example.apis.apilearning.exceptions.ResourcesNotFound;
import com.example.apis.apilearning.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class EmployeeServices {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServices(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDto> getEmployeeById(Long id) {

//        Optional<EmployeeEntity> employeeEntity =  employeeRepository.findById(id);
//         employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity, EmployeeDto.class));

        return employeeRepository.findById(id).map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployee() {

        List<EmployeeEntity> employeeEntitiesList = employeeRepository.findAll();
        return  employeeEntitiesList
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee) {
        EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
    }

    public boolean isEmployeeExistsById(Long employeeId)  {
        boolean isExists =  employeeRepository.existsById(employeeId);
        if(!isExists) throw new ResourcesNotFound("Employee not found with id: "+ employeeId);
        return  true;
    }

    public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto updateValue) {
        isEmployeeExistsById(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(updateValue, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployedEntity, EmployeeDto.class);
    }

    public boolean deleteEmployeeBuId(Long employeeId) {
        isEmployeeExistsById(employeeId);
        employeeRepository.deleteById(employeeId);
       return true;
    }

    public EmployeeDto partiallyUpdateTheEmployeeDetails(Long employeeId, Map<String, Object> fieldToBeUpdated) {

        isEmployeeExistsById(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        fieldToBeUpdated.forEach( (field,value) -> {
            Field field1 = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            field1.setAccessible(true);
            ReflectionUtils.setField(field1,employeeEntity, value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
    }
}
