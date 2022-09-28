package com.swa.application.controller;

import com.swa.application.dto.ApplicationDto;
import com.swa.application.repository.ApplicationRepository;
import com.swa.application.response.ServerResponse;
import com.swa.application.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
    public ResponseEntity<ServerResponse> save(@RequestBody ApplicationDto applicationDto){
        return new ResponseEntity<>(applicationService.save(applicationDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> findAll(){
        List<ApplicationDto> applicationDtos = applicationService.findAll();
        return new ResponseEntity<>(applicationDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationDto> findById(@PathVariable String id){
        return new ResponseEntity<>(applicationService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApplicationDto> update(@RequestBody ApplicationDto applicationDto){
        applicationDto = applicationService.update(applicationDto);
        return new ResponseEntity<>(applicationDto, HttpStatus.OK);
    }


}
