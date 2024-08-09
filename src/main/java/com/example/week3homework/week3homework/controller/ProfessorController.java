package com.example.week3homework.week3homework.controller;


import com.example.week3homework.week3homework.entities.ProfessorEntity;
import com.example.week3homework.week3homework.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(path = "/{professorId}")
    public ProfessorEntity getProfessorById(@PathVariable Long professorId){
        return professorService.getProfessorById(professorId);
    }

    @PostMapping
    public ProfessorEntity createNewProfessor(@RequestBody ProfessorEntity professorEntity){
        return professorService.createNewProfessor(professorEntity);
    }

    @PutMapping(path = "/{professorId}/subject/{subjectId}")
    public ProfessorEntity assignSubjectToProfessor(@PathVariable Long professorId, @PathVariable Long subjectId){
        return professorService.assignSubjectToProfessor(professorId, subjectId);
    }

    @PutMapping(path = "/{professorId}/student/{studentId}")
    public ProfessorEntity assignStudentsToProfessor(@PathVariable Long professorId, @PathVariable Long studentId){
        return professorService.assignStudentsToProfessor(professorId, studentId);
    }
}
