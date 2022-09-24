package com.swa.recruiterservice.repository;

import com.swa.recruiterservice.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends MongoRepository<Company, Long> {
    Company findByName(String name);
}
