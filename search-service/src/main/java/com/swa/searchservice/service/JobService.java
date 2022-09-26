package com.swa.searchservice.service;

import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.searchservice.entity.JobTable;
import com.swa.searchservice.helper.cassandra.CassandraPage;
import com.swa.searchservice.helper.cassandra.Paginated;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;

import java.util.List;

public interface JobService {

    CassandraPage<JobTable> getAllJobs(Paginated paginated);

    JobDTO getJobById(String jobId);

    JobDTO saveJobTable(JobDTO jobDTO);

    JobDTO updateJob(JobDTO jobDTO);
}
