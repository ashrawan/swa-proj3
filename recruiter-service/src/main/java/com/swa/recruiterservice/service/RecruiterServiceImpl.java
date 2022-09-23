package com.swa.recruiterservice.service;

import com.swa.recruiterservice.domain.Company;
import com.swa.recruiterservice.domain.Recruiter;
import com.swa.recruiterservice.model.RecruiterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterService {
    @Override
    public List<Recruiter> findAllRecruiters() {
        return null;
    }

    @Override
    public List<Company> findAllCompanies() {
        return null;
    }

    @Override
    public RecruiterDto createRecruiter(RecruiterDto recruiterDto) {
        return null;
    }
}
