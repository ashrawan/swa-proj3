package com.swa.application.dto.mapper;

import com.swa.application.dto.ApplicationDto;
import com.swa.application.model.Application;
import com.swa.proj3commonmodule.security.AppSecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class ApplicationMapper {

    public Application mapToApplication(ApplicationDto applicationDto){
//        Optional<Long> optionalUserId = AppSecurityUtils.getCurrentUserId();

        return Application.builder()
                .resumeVersion(applicationDto.getResumeVersion())
                .candidateId(applicationDto.getCandidateId())
                .jobId(applicationDto.getJobId())
                .date(applicationDto.getDate())
                .build();
    }

    public ApplicationDto mapToDto(Application application){
        return ApplicationDto.builder()
                .id(application.getId())
                .resumeVersion(application.getResumeVersion())
                .candidateId(application.getCandidateId())
                .jobId(application.getJobId())
                .date(application.getDate())
                .status(false)
                .build();
    }

}
