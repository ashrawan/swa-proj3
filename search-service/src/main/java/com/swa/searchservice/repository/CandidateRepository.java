package com.swa.searchservice.repository;

import com.swa.searchservice.entity.CandidateTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends CassandraRepository<CandidateTable, UUID> {

}
