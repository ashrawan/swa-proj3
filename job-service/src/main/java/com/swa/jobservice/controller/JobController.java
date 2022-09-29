package com.swa.jobservice.controller;

import com.swa.jobservice.service.JobService;
import com.swa.proj3commonmodule.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JobDTO jobDTO) {
        return new ResponseEntity<>(jobService.createJob(jobDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return new ResponseEntity<>(jobService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllJob() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }


}
