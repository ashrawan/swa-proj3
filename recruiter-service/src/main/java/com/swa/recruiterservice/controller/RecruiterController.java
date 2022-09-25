package com.swa.recruiterservice.controller;

import com.swa.recruiterservice.domain.Company;
import com.swa.recruiterservice.model.RecruiterDto;
import com.swa.recruiterservice.service.RecruiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> listRecuiter(){
        log.debug("Finding recuiters ");
        return new ResponseEntity<>(recruiterService.findAllRecruiters(), HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<?> listCompanies(){
        log.debug("Finding companies ");
        return new ResponseEntity<>(recruiterService.findAllCompanies(), HttpStatus.OK);
    }

        @PostMapping("/create-recruiter")
    public ResponseEntity<?> createRecruiter(@RequestBody RecruiterDto recruiterDto) {
        log.info("Creating recruiters");
        RecruiterDto returnedDto = recruiterService.createRecruiter(recruiterDto);
        return new ResponseEntity<>(returnedDto, HttpStatus.CREATED);
    }
}
