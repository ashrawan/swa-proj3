package com.swa.searchservice.service.impl;

import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.proj3commonmodule.exceptions.NotFoundException;
import com.swa.searchservice.entity.JobTable;
import com.swa.searchservice.helper.cassandra.CassandraHelperUtil;
import com.swa.searchservice.helper.cassandra.CassandraPage;
import com.swa.searchservice.helper.cassandra.Paginated;
import com.swa.searchservice.helper.mapper.JobMapper;
import com.swa.searchservice.repository.JobRepository;
import com.swa.searchservice.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Cacheable(value = "jobs", key = "'all'", condition = "#result != null && result.size() > 0")
    @Override
    public CassandraPage<JobTable> getAllJobs(Paginated paginated) {
        CassandraPageRequest cassandraPageRequest = CassandraHelperUtil
                .createCassandraPageRequest(paginated.getLimit(), paginated.getPagingState());
        final Slice<JobTable> jobTableSlice = jobRepository.findAll(cassandraPageRequest);
        CassandraPage<JobTable> jobTableCassandraPage = new CassandraPage<>(jobTableSlice);
//        List<JobDTO> jobDTOS = jobMapper.toDtoList(jobTableSlice.getContent());
        return jobTableCassandraPage;
    }

    @CachePut(value = "jobs", condition = "#result.jobId != null", key = "#result.jobId")
    @CacheEvict(value = "jobs", key = "'all'")
    @Override
    public JobDTO saveJobTable(JobDTO jobDTO) {
        JobTable jobTable = jobMapper.toEntity(jobDTO);
        JobTable savedJob = jobRepository.save(jobTable);
        return jobMapper.toDto(savedJob);
    }

    @Cacheable(value = "jobs", key = "#jobId")
    @Override
    public JobDTO getJobById(String jobId) {
        log.info("Get Job From DB with ID: " + jobId);
        UUID uuid = UUID.fromString(jobId);
        JobTable foundJob = jobRepository.findById(uuid).orElseThrow(() -> new NotFoundException());
        return jobMapper.toDto(foundJob);

    }

    @CachePut(value = "jobs", key = "#jobDTO.jobId")
    @CacheEvict(value = "jobs", key = "'all'")
    @Override
    public JobDTO updateJob(JobDTO jobDTO) {
        JobTable foundJobTable = jobRepository.findById(UUID.fromString(jobDTO.getJobId()))
                .orElseThrow(() -> new NotFoundException());
        foundJobTable.setTitle(jobDTO.getTitle());
        foundJobTable.setDescription(jobDTO.getDescription());
        jobRepository.save(foundJobTable);
        return jobMapper.toDto(foundJobTable);
    }
}