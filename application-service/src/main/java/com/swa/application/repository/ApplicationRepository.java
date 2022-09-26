package com.swa.application.repository;

import com.swa.application.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
    Optional<Application> findByCandidateIdAndJobId(String candidateId, String jobId);
}
