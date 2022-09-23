package com.swa.recruiterservice.repository;

import com.swa.recruiterservice.domain.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
}
