package com.swa.searchservice.controller;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.searchservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/{candidateId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCandidateById(@PathVariable String candidateId) {
        CandidateDTO candidateDTO = searchService.getCandidateById(candidateId);
        return new ResponseEntity<>(candidateDTO, HttpStatus.OK);
    }
}
