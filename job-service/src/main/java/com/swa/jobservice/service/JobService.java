package com.swa.jobservice.service;

import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.proj3commonmodule.dto.JobRequest;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO);
    JobDTO findById(String id);
    List<JobDTO> findAll();
    HttpStatus existJob(JobRequest dto);
}
