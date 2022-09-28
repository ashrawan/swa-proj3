package com.example.searchserviceelastic.service;

import com.example.searchserviceelastic.entity.JobEntity;

import java.io.IOException;
import java.util.List;

public interface JobService {
    List<JobEntity> findAllByTitle(String jobTitle);

    JobEntity findOne(String id);

    JobEntity save(JobEntity jobEntity);

    JobEntity update(JobEntity jobEntity);

    void deleteById(String id);
}
