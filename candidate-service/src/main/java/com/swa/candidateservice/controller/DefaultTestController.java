package com.swa.candidateservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DefaultTestController {

    @GetMapping
    public ResponseEntity<?> defaultResponse() {
        return new ResponseEntity<>("Working", HttpStatus.OK);
    }
}
