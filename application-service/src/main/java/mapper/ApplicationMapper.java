package com.swa.apply.dto.mapper;

import com.swa.apply.dto.ApplicationDto;
import com.swa.apply.model.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public Application mapToApplication(ApplicationDto applicationDto){
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
                .build();
    }

}
