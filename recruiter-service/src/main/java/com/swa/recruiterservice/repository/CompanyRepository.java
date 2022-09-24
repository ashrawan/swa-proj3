package com.swa.recruiterservice.repository;

import com.swa.recruiterservice.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
}
