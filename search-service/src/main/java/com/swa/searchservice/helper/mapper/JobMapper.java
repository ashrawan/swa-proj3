package com.swa.searchservice.helper.mapper;

import com.swa.proj3commonmodule.dto.GenericMapper;
import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.searchservice.entity.JobTable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JobMapper implements GenericMapper<JobDTO, JobTable> {

    @Override
    public JobDTO toDto(JobTable jobTable) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(jobTable.getJobId().toString());
        jobDTO.setTitle(jobTable.getTitle());
        jobDTO.setDescription(jobTable.getDescription());
        return jobDTO;
    }

    @Override
    public JobTable toEntity(JobDTO jobDTO) {
        JobTable jobTable = new JobTable();
        jobTable.setJobId(UUID.fromString(jobDTO.getJobId()));
        jobTable.setTitle(jobDTO.getTitle());
        jobTable.setDescription(jobDTO.getDescription());
        return jobTable;
    }
}
