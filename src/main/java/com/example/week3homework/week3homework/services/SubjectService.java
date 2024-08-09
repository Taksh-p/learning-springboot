package com.example.week3homework.week3homework.services;

import com.example.week3homework.week3homework.entities.SubjectEntity;
import com.example.week3homework.week3homework.repositories.ProfessorRepository;
import com.example.week3homework.week3homework.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;

    }

    public SubjectEntity getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public SubjectEntity createNewSubject(SubjectEntity subjectEntity){
        return subjectRepository.save(subjectEntity);
    }

}
