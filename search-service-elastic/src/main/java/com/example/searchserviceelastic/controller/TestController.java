package com.example.searchserviceelastic.controller;

import com.example.searchserviceelastic.entity.CandidateEntity;
import com.example.searchserviceelastic.entity.JobEntity;
import com.example.searchserviceelastic.service.CandidateService;
import com.example.searchserviceelastic.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "test")
public class TestController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CandidateService candidateService;

    @RequestMapping(value = "job/save", method = RequestMethod.GET)
    public ResponseEntity<?> testJobSave() {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(UUID.randomUUID().toString());
        jobEntity.setDescription("Test description");
        jobEntity.setTitle("Software Developer");
        JobEntity savedJobEntity = jobService.save(jobEntity);
        return new ResponseEntity<>(savedJobEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "candidate/save", method = RequestMethod.GET)
    public ResponseEntity<?> testCandidateSave() {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setCandidateId(UUID.randomUUID().toString());
        candidateEntity.setFullName("Candidate Test");
        candidateEntity.setSummary("Summary, Candidate Summary.");
        CandidateEntity savedCandidate = candidateService.save(candidateEntity);
        return new ResponseEntity<>(savedCandidate, HttpStatus.OK);
    }
}
