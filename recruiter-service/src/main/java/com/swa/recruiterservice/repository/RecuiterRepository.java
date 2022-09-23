package com.swa.recruiterservice.repository;

import com.swa.recruiterservice.domain.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecuiterRepository extends JpaRepository<Recruiter, Long> {
}
