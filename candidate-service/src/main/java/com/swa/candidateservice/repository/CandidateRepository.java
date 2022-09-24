package com.swa.candidateservice.repository;

import com.swa.candidateservice.entity.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

}
