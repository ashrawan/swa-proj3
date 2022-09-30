package com.swa.recruiterservice.service;

import com.swa.recruiterservice.domain.Company;
import com.swa.recruiterservice.domain.Recruiter;
import com.swa.recruiterservice.mappers.Mapper;
import com.swa.recruiterservice.model.RecruiterDto;
import com.swa.recruiterservice.repository.CompanyRepository;
import com.swa.recruiterservice.repository.RecruiterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final CompanyRepository companyRepository;
    private final Mapper mapper;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository, CompanyRepository companyRepository, Mapper mapper) {
        this.recruiterRepository = recruiterRepository;
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Recruiter> findAllRecruiters() {
        log.debug("Retrieving recruiters from recruiter repository ");
        return recruiterRepository.findAll();
    }

    @Override
    public List<Company> findAllCompanies() {
        log.debug("Retrieving companies from recruiter repository ");
        List<Company> list = recruiterRepository.findAll()
                .stream()
                .map(Recruiter::getCompanies)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return list;

    }

    @Override
    public RecruiterDto createRecruiter(RecruiterDto recruiterDto) {

        log.debug("Reading from dto and creating companies if not exist ");
        recruiterDto.getCompanies().forEach(company -> {
             if(companyRepository.findByName(company.getName().toLowerCase(Locale.ROOT)) == null){
                 companyRepository.save(new Company(company.getName().toLowerCase()));
             }
        });

        log.debug("mapping dtos ");
        Recruiter recruiter = mapper.recruiterDtoToRecruiter(recruiterDto);

        recruiter.getCompanies().removeAll(recruiter.getCompanies());

        log.debug("setting companies recruiter work with ");
        recruiterDto.getCompanies().forEach(company -> {
            Company foundCompany = companyRepository.findByName(company.getName().toLowerCase());
            recruiter.getCompanies().add(foundCompany);
        });

        Recruiter savedRecruiter = recruiterRepository.save(recruiter);

        return mapper.recruiterToRecruiterDto(savedRecruiter);
    }
}
