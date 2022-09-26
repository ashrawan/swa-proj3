package com.swa.searchservice.controller;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.searchservice.entity.JobTable;
import com.swa.searchservice.helper.cassandra.CassandraPage;
import com.swa.searchservice.helper.cassandra.Paginated;
import com.swa.searchservice.service.CandidateService;
import com.swa.searchservice.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class SearchController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CandidateService candidateService;

    @RequestMapping(value = "job/all", method = RequestMethod.GET)
    public ResponseEntity<?> jobDataResponse(final @Valid Paginated paginated) {
        CassandraPage<JobTable> allJobs = jobService.getAllJobs(paginated);
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @RequestMapping(value = "job/{jobId}", method = RequestMethod.GET)
    public ResponseEntity<?> getJobById(@PathVariable String jobId) {
        log.info("API - getJobById called, ID {}", jobId);
        JobDTO jobDTO = jobService.getJobById(jobId);
        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "candidate/all", method = RequestMethod.GET)
    public ResponseEntity<?> canidateDataResponse() {
        List<CandidateDTO> allCandidates = candidateService.getAllCandidates();
        return new ResponseEntity<>(allCandidates, HttpStatus.OK);
    }

    @RequestMapping(value = "candidate/{candidateId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCandidateById(@PathVariable String candidateId) {
        log.info("API - getCandidateById called, ID {}", candidateId);
        CandidateDTO candidateDTO = candidateService.getCandidateById(candidateId);
        return new ResponseEntity<>(candidateDTO, HttpStatus.OK);
    }

}
