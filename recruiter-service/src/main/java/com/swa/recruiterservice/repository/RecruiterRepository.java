package com.swa.recruiterservice.repository;

import com.swa.recruiterservice.domain.Company;
import com.swa.recruiterservice.domain.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

}
