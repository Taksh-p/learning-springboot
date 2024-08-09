package com.example.week3homework.week3homework.services;

import com.example.week3homework.week3homework.entities.AdmissionRecordEntity;
import com.example.week3homework.week3homework.entities.ProfessorEntity;
import com.example.week3homework.week3homework.entities.StudentEntity;
import com.example.week3homework.week3homework.entities.SubjectEntity;
import com.example.week3homework.week3homework.repositories.ProfessorRepository;
import com.example.week3homework.week3homework.repositories.StudentRepository;
import com.example.week3homework.week3homework.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public ProfessorService(ProfessorRepository professorRepository, SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    public ProfessorEntity getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElse(null);
    }

    public ProfessorEntity createNewProfessor(ProfessorEntity professorEntity){
        return professorRepository.save(professorEntity);
    }

    public ProfessorEntity assignSubjectToProfessor(Long professorId, Long subjectId){
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);

        return professorEntity.flatMap(professor ->
                subjectEntity.map(subject ->{
                    subject.setSubjectProfessor(professor);
                    subjectRepository.save(subject);

                    professor.getSubjects().add(subject);
                    return professor;
                })).orElse(null);
    }

    public ProfessorEntity assignStudentsToProfessor(Long professorId, Long studentId) {

        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return professorEntity.flatMap(professor ->
                studentEntity.map(student ->{
                    student.getProfessors().add(professor);
                    studentRepository.save(student);

                    professor.getStudents().add(student);
                    return professor;
                })).orElse(null);
    }

}
