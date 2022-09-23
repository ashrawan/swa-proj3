package com.swa.recruiterservice.service;

import com.swa.recruiterservice.domain.JobDescription;
import com.swa.recruiterservice.model.JobDescriptionDto;
import com.swa.recruiterservice.model.RecruiterDto;

import java.util.List;

public interface JobDescriptionService {
    JobDescriptionDto createJobDes(JobDescriptionDto jobDescriptionDto);
    List<JobDescriptionDto> findAll();
}
