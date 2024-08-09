package com.example.week3homework.week3homework.controller;

import com.example.week3homework.week3homework.entities.ProfessorEntity;
import com.example.week3homework.week3homework.entities.StudentEntity;
import com.example.week3homework.week3homework.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/{studentId}")
    public StudentEntity getStudentById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public StudentEntity createNewStudent(@RequestBody StudentEntity studentEntity){
        return studentService.createNewStudent(studentEntity);
    }

    @PutMapping(path = "/{studentId}/professor/{professorId}")
    public StudentEntity assignProfessorToStudent( @PathVariable Long studentId, @PathVariable Long professorId){
        return studentService.assignProfessorToStudent(studentId, professorId);
    }
}
