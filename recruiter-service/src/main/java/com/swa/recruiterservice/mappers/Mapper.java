package com.swa.recruiterservice.mappers;

import com.swa.recruiterservice.domain.Recruiter;
import com.swa.recruiterservice.model.RecruiterDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    public RecruiterDto recruiterToRecruiterDto(Recruiter recruiter){
        RecruiterDto recruiterDto = RecruiterDto.builder()
                .name(recruiter.getName())
                .email(recruiter.getEmail())
                .phone(recruiter.getPhone())
                .recruiterCompany(recruiter.getRecruiterCompany())
                .companies(recruiter.getCompanies())
                .build();

        return recruiterDto;
    }


    public Recruiter recruiterDtoToRecruiter(RecruiterDto recruiterDto){

        Recruiter recruiter = Recruiter.builder()
                .name(recruiterDto.getName())
                .email(recruiterDto.getEmail())
                .phone(recruiterDto.getPhone())
                .recruiterCompany(recruiterDto.getRecruiterCompany())
                .companies(recruiterDto.getCompanies())
                .build();

        return  recruiter;
    }
}
