package com.example.week3homework.week3homework.services;

import com.example.week3homework.week3homework.entities.ProfessorEntity;
import com.example.week3homework.week3homework.entities.StudentEntity;
import com.example.week3homework.week3homework.repositories.ProfessorRepository;
import com.example.week3homework.week3homework.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public StudentService(StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    public StudentEntity getStudentById(Long studentId) {
        return  studentRepository.findById(studentId).orElse(null);
    }

    public StudentEntity createNewStudent(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }

    public StudentEntity assignProfessorToStudent(Long studentId, Long professorId) {

        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return studentEntity.flatMap(student ->
                professorEntity.map(professor ->{

                    professor.getStudents().add(student);
                    professorRepository.save(professor);

                    student.getProfessors().add(professor);
                    return student;
                })).orElse(null);
    }
}
