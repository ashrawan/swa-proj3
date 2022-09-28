package com.swa.jobservice.service;

import com.swa.jobservice.entity.Job;
import com.swa.jobservice.repository.JobRepository;
import com.swa.proj3commonmodule.dto.JobDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceImplTest {

    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobServiceImpl jobService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createJob() {

        Job jobToSave = getJob();

        when(jobRepository.save(any())).thenReturn(jobToSave);

        JobDTO jobDTO = JobDTO.builder().title(jobToSave.getTitle()).description(jobToSave.getDescription()).build();

        JobDTO savedJob = jobService.createJob(jobDTO);

        assertNotNull(savedJob);
        assertEquals(savedJob.getTitle(), jobToSave.getTitle());
    }

    private Job getJob() {
        return Job.builder().title("Job Title").description("Job Title Description").build();
    }

    @Test
    void findById() {
        String id = "someid";

        Optional<Job> job = Optional.ofNullable(Job.builder().jobId(id).build());

        when(jobRepository.findById(any())).thenReturn(job);

        JobDTO jobFound =  jobService.findById(id);

        assertEquals(id, jobFound.getJobId());

        verify(jobRepository).findById(anyString());

    }

    @Test
    void findAll() {

        List<Job> jobs = new ArrayList<>();
        jobs.add(Job.builder().title("Job Title").build());

        when(jobRepository.findAll()).thenReturn(jobs);

        List<JobDTO> returnList = jobService.findAll();

        assertEquals(returnList.size(), 1);

        verify(jobRepository, times(1)).findAll();
    }

}