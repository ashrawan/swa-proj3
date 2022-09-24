package com.swa.recruiterservice.service;

import com.swa.recruiterservice.domain.Company;
import com.swa.recruiterservice.domain.Recruiter;
import com.swa.recruiterservice.model.RecruiterDto;

import java.util.List;

public interface RecruiterService {
    List<Recruiter> findAllRecruiters();
    List<Company> findAllCompanies();
    RecruiterDto createRecruiter(RecruiterDto recruiterDto);
}
