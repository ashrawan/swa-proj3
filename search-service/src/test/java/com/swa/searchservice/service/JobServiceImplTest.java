package com.swa.searchservice.service;

import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.proj3commonmodule.exceptions.NotFoundException;
import com.swa.searchservice.entity.JobTable;
import com.swa.searchservice.helper.mapper.JobMapper;
import com.swa.searchservice.repository.JobRepository;
import com.swa.searchservice.service.impl.JobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    private JobMapper jobMapper;

    private JobServiceImpl jobService;

    private JobDTO jobDTO;

    private String uuid = UUID.randomUUID().toString();

    @BeforeEach()
    public void setUp() {
        jobMapper = new JobMapper();
        jobService = new JobServiceImpl(jobRepository, jobMapper);
        jobDTO = new JobDTO();
        jobDTO.setJobId(uuid);
        jobDTO.setTitle("Software Developer");
    }

    @Test
    public void testGetAllJobs_thenResultJobList() {

        List<JobTable> jobTableList = new ArrayList<>();
        when(jobRepository.findAll()).thenReturn(jobTableList);
        Assertions.assertEquals(jobService.getAllJobs(), jobTableList);
    }

    @Test
    public void testGetJobById_ResultJob() {

        JobTable jobTable = jobMapper.toEntity(jobDTO);
        UUID jobUUID = UUID.fromString(jobDTO.getJobId());
        when(jobRepository.findById(jobUUID)).thenReturn(Optional.ofNullable(jobTable));
        Assertions.assertEquals(jobDTO, jobService.getJobById(jobDTO.getJobId()));
    }

    @Test
    public void testSaveJob_thenResultJob() {

        JobTable jobTable = jobMapper.toEntity(jobDTO);
        when(jobRepository.save(jobTable)).thenReturn(jobTable);
        Assertions.assertEquals(jobService.saveJobTable(jobDTO), jobDTO);
    }

    @Test
    public void testGetByJobIdWhichIsNotAvailable_thenExpectedException() {

        JobTable savedJobData = jobMapper.toEntity(jobDTO);
        UUID jobUUID = UUID.fromString(jobDTO.getJobId());
        lenient().when(jobRepository.findById(jobUUID)).thenReturn(Optional.ofNullable(savedJobData));

        Assertions.assertThrows(NotFoundException.class, () -> {
            jobService.getJobById(UUID.randomUUID().toString());
        });
    }

    @Test
    public void testUpdateJob_thenUpdatedJob() {

        JobTable savedJobData = jobMapper.toEntity(jobDTO);
        UUID jobUUID = UUID.fromString(jobDTO.getJobId());
        when(jobRepository.findById(jobUUID)).thenReturn(Optional.of(savedJobData));
        when(jobRepository.save(savedJobData)).thenReturn(savedJobData);
        Assertions.assertEquals(jobService.updateJob(jobDTO), jobDTO);
    }

}