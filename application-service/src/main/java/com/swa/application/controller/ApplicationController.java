package com.swa.application.controller;

import com.swa.application.dto.ApplicationDto;
import com.swa.application.model.Application;
import com.swa.application.repository.ApplicationRepository;
import com.swa.application.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apply")
@Slf4j
public class ApplicationController {

    @Autowired private ApplicationService applicationService;

    @Autowired private ApplicationRepository applicationRepository;

    @GetMapping("status")
    public String status(){
        log.info("----- Checking application-service status");
        return " APPLICATION_SERVICE running ...";
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ApplicationDto applicationDto){
       return applicationService.save(applicationDto);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> findAll(){
        List<ApplicationDto> applicationDtos = applicationService.findAll();
        return new ResponseEntity<>(applicationDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationDto> findById(@PathVariable Integer id){
        ApplicationDto applicationDto = applicationService.findById(id);
        return new ResponseEntity<>(applicationDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApplicationDto> update(@RequestBody ApplicationDto applicationDto){
        applicationDto = applicationService.update(applicationDto);
        return new ResponseEntity<>(applicationDto, HttpStatus.OK);
    }


}
