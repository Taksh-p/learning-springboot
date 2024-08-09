package com.example.week3homework.week3homework.controller;

import com.example.week3homework.week3homework.entities.AdmissionRecordEntity;
import com.example.week3homework.week3homework.services.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admissionRecord")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping(path = "/{admissionRecordId}")
    public AdmissionRecordEntity getAdmissionRecordById(@PathVariable Long admissionRecordId){
        return admissionRecordService.getAdmissionRecordById(admissionRecordId);
    }

    @PostMapping
    public AdmissionRecordEntity createNewAdmissionRecord(@RequestBody AdmissionRecordEntity admissionRecordEntity){
        return  admissionRecordService.createNewAdmissionRecord(admissionRecordEntity);
    }

    @PutMapping(path = "/{admissionRecordId}/student/{studentId}")
    public AdmissionRecordEntity assignStudentToAdmissionRecord(
            @PathVariable Long admissionRecordId,
            @PathVariable Long studentId){

        return admissionRecordService.assignStudentToAdmissionRecord(admissionRecordId, studentId);
    }

}
