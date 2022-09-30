package com.example.searchserviceelastic.controller;

import com.example.searchserviceelastic.entity.CandidateEntity;
import com.example.searchserviceelastic.entity.JobEntity;
import com.example.searchserviceelastic.service.CandidateService;
import com.example.searchserviceelastic.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SearchController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CandidateService candidateService;

    @RequestMapping(value = "job/all", method = RequestMethod.GET)
    public ResponseEntity<?> jobDataResponse(@RequestParam(value = "title", required = false) String title) {
        List<JobEntity> allJobs = jobService.findAllByTitle(title);
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @RequestMapping(value = "job/{jobId}", method = RequestMethod.GET)
    public ResponseEntity<?> getJobById(@PathVariable String jobId) {
        log.info("API - getJobById called, ID {}", jobId);
        JobEntity jobEntity = jobService.findOne(jobId);
        return new ResponseEntity<>(jobEntity, HttpStatus.OK);
    }


    @RequestMapping(value = "candidate/all", method = RequestMethod.GET)
    public ResponseEntity<?> canidateDataResponse(@RequestParam("fullName") String fullName) {
        List<CandidateEntity> allCandidates = candidateService.findAllByName(fullName);
        return new ResponseEntity<>(allCandidates, HttpStatus.OK);
    }

    @RequestMapping(value = "candidate/{candidateId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCandidateById(@PathVariable String candidateId) {
        log.info("API - getCandidateById called, ID {}", candidateId);
        CandidateEntity candidateEntity = candidateService.findOne(candidateId);
        return new ResponseEntity<>(candidateEntity, HttpStatus.OK);
    }

}
