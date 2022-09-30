package com.swa.recruiterservice.controller;

import com.swa.recruiterservice.model.RecruiterDto;
import com.swa.recruiterservice.service.RecruiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class RecruiterController {


    private final RecruiterService recruiterService;
//    private final KafkaTemplate<String, String> kafkaTemplate;

//    @Value("${kafka.topic}")
//    private String kafkatopic;


    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> listRecuiter() {
        log.debug("Finding recuiters ");

        return new ResponseEntity<>(recruiterService.findAllRecruiters(), HttpStatus.OK);
    }

    //Testing kakfa
//    @GetMapping("/test")
//    @ResponseStatus(HttpStatus.OK)
//    public void test(){
//        log.debug("testing kafka");
//
//        kafkaTemplate.send(kafkatopic, recruiterService.findAllRecruiters().stream().findFirst().toString());
//    }

    @GetMapping("/companies")
    public ResponseEntity<?> listCompanies() {
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
