package com.swa.recruiterservice.controller;


import com.swa.recruiterservice.model.JobDescriptionDto;
import com.swa.recruiterservice.model.RecruiterDto;
import com.swa.recruiterservice.service.JobDescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/joboperation")
public class JobOperationController {

    private final JobDescriptionService jobDescriptionService;

    public JobOperationController(JobDescriptionService jobDescriptionService) {
        this.jobDescriptionService = jobDescriptionService;
    }


    @PostMapping("/create-jobdescription")
    public ResponseEntity<?> jobdescription(@RequestBody JobDescriptionDto jobDescriptionDto) {
        JobDescriptionDto returnedDto = jobDescriptionService.createJobDes(jobDescriptionDto);
        return new ResponseEntity<>(returnedDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allJobsDescription(){
        log.debug("Finding all ");
        return new ResponseEntity<>(jobDescriptionService.findAll(), HttpStatus.OK);
    }

}
