package com.example.week3homework.week3homework.services;

import com.example.week3homework.week3homework.entities.AdmissionRecordEntity;
import com.example.week3homework.week3homework.entities.StudentEntity;
import com.example.week3homework.week3homework.repositories.AdmissionRecordRepository;
import com.example.week3homework.week3homework.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
    }

    public AdmissionRecordEntity getAdmissionRecordById(Long admissionRecordId) {
        return admissionRecordRepository.findById(admissionRecordId).orElse(null);
    }

    public AdmissionRecordEntity createNewAdmissionRecord(AdmissionRecordEntity admissionRecordEntity) {
        return admissionRecordRepository.save(admissionRecordEntity);
    }

    public AdmissionRecordEntity assignStudentToAdmissionRecord(Long admissionRecordId, Long studentId) {

        Optional<AdmissionRecordEntity> admissionRecordEntity = admissionRecordRepository.findById(admissionRecordId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return admissionRecordEntity.flatMap(admissionRecord ->
                studentEntity.map(student ->{
                    admissionRecord.setStudent(student);
                    return admissionRecordRepository.save(admissionRecord);
                })).orElse(null);
    }
}
