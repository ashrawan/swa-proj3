package com.swa.jobservice.service;

import com.swa.proj3commonmodule.dto.JobDTO;

import java.util.List;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO);
    JobDTO findById(String id);
    List<JobDTO> findAll();
}
