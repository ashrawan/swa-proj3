package com.swa.searchservice.controller;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.searchservice.service.CandidateService;
import com.swa.searchservice.service.JobService;
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
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(UUID.randomUUID().toString());
        jobDTO.setDescription("Test description");
        jobDTO.setTitle("Software Developer");
        JobDTO savedJobDto = jobService.saveJobTable(jobDTO);
        return new ResponseEntity<>(savedJobDto, HttpStatus.OK);
    }

    @RequestMapping(value = "candidate/save", method = RequestMethod.GET)
    public ResponseEntity<?> testCandidateSave() {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateID(UUID.randomUUID().toString());
        candidateDTO.setFullName("Candidate Test");
        candidateDTO.setSummary("Summary, This candidate will return unique id each time, if its not from cache.");
        CandidateDTO savedCandidate = candidateService.saveCandidate(candidateDTO);
        return new ResponseEntity<>(savedCandidate, HttpStatus.OK);
    }
}
