package com.swa.searchservice.repository;

import com.swa.searchservice.entity.JobTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends CassandraRepository<JobTable, UUID> {

}