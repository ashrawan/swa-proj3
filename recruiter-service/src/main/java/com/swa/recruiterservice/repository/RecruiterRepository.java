package com.swa.recruiterservice.repository;


import com.swa.recruiterservice.domain.Recruiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecruiterRepository extends MongoRepository<Recruiter, Long> {

}
