package com.swa.candidateservice.controller;

import com.swa.candidateservice.service.CandidateService;
import com.swa.proj3commonmodule.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping("/create-candidate-profile")
//    @CircuitBreaker(name = "defaultsForCandidateServiceApp", fallbackMethod = "candidateServiceFallback")
    public ResponseEntity<?> createCandidateProfile(@RequestBody CandidateDTO candidateDTO) {
        return new ResponseEntity<>(candidateService.registerCandidate(candidateDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return new ResponseEntity<>(candidateService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
//    @Retry(name = "retryForCandidates", fallbackMethod = "candidateServiceFallback")
    public ResponseEntity<?> getAllCandidate() {
        return new ResponseEntity<>(candidateService.findAll(), HttpStatus.OK);
    }

    private CandidateDTO candidateServiceFallback(CandidateDTO candidateDTO, Throwable throwable) {
        return candidateService.registerCandidate(candidateDTO);
    }
}
