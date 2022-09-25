package com.swa.jobservice.service;

import com.swa.jobservice.entity.Job;
import com.swa.jobservice.repository.JobRepository;
import com.swa.proj3commonmodule.dto.JobDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;
    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = Job.builder()
                .title(jobDTO.getTitle())
                .description(jobDTO.getDescription())
                .position(jobDTO.getPosition())
                .category(jobDTO.getCategory())
                .skills(jobDTO.getSkills())
                .build();
        Job saveJob = jobRepository.save(job);
        jobDTO.setJobId(saveJob.getJobId());
        return jobDTO;
    }

    @Override
    public JobDTO findById(String id) {
        Job job = jobRepository.findById(id).get();
        if(job == null) {
            // log.error("Job with id {} not found!!",id);
            throw new RuntimeException("Job Not Found!!");
        }
        JobDTO jobDTO = JobDTO.builder()
                .jobId(job.getJobId())
                .title(job.getTitle())
                .description(job.getDescription())
                .position(job.getPosition())
                .category(job.getCategory())
                .skills(job.getSkills())
                .build();
        return jobDTO;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobList = jobRepository.findAll();
        if(jobList == null) {
            throw new RuntimeException("Jobs is empty!!");
        }
        List<JobDTO> jobDTOList = jobList.stream()
                .map(job -> {
                    JobDTO jobDTO = new JobDTO();
                    jobDTO.setJobId(job.getJobId());
                    jobDTO.setTitle(job.getTitle());
                    jobDTO.setDescription(job.getDescription());
                    jobDTO.setCategory(job.getCategory());
                    jobDTO.setPosition(job.getPosition());
                    jobDTO.setSkills(job.getSkills());
                    return jobDTO;
                }).collect(Collectors.toList());
        return jobDTOList;
    }
}
