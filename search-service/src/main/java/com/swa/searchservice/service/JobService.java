package com.swa.searchservice.service;

import com.swa.proj3commonmodule.dto.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> getAllJobs();

    JobDTO getJobById(String jobId);

    JobDTO saveJobTable(JobDTO jobDTO);

    JobDTO updateJob(JobDTO jobDTO);
}
