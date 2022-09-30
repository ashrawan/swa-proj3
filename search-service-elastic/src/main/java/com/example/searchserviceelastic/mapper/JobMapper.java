package com.example.searchserviceelastic.mapper;

import com.example.searchserviceelastic.entity.JobEntity;
import com.swa.proj3commonmodule.dto.GenericMapper;
import com.swa.proj3commonmodule.dto.JobDTO;
import org.springframework.stereotype.Component;

@Component
public class JobMapper implements GenericMapper<JobDTO, JobEntity> {

    @Override
    public JobDTO toDto(JobEntity jobEntity) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(jobEntity.getJobId());
        jobDTO.setTitle(jobEntity.getTitle());
        jobDTO.setDescription(jobEntity.getDescription());
        return jobDTO;
    }

    @Override
    public JobEntity toEntity(JobDTO jobDTO) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(jobDTO.getJobId());
        jobEntity.setTitle(jobDTO.getTitle());
        jobEntity.setDescription(jobDTO.getDescription());
        return jobEntity;
    }
}
