package com.swa.application.service.impl;

import com.swa.application.config.ApplicationListener;
import com.swa.application.dto.ApplicationDto;
import com.swa.application.dto.mapper.ApplicationMapper;
import com.swa.application.model.Application;
import com.swa.application.repository.ApplicationRepository;
import com.swa.application.service.ApplicationService;
import com.swa.proj3commonmodule.dto.JobRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired private ApplicationMapper mapper;
    @Autowired private ApplicationRepository applicationRepository;

    @Value("${spring.kafka.application_topic}")
    private String applicationTopic;

    @Autowired private ApplicationListener applicationListener;

    @Autowired
    private KafkaTemplate<String, JobRequest> kafkaTemplate;

    @Override
    public ResponseEntity<?> save(ApplicationDto applicationDto) {
        log.info("------------ Applying application");
        String message = null;
        Optional<Application> applicationOpt = applicationRepository.findByCandidateIdAndJobId(applicationDto.getCandidateId(), applicationDto.getJobId());
        if(!applicationOpt.isPresent())
            return persistApplication(applicationDto);
        else
            message = modifyApplication(applicationOpt);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    private ResponseEntity<String> persistApplication(ApplicationDto applicationDto) {
        sendJob(applicationDto.getCandidateId(), applicationDto.getJobId());
        Application application = mapper.mapToApplication(applicationDto);
        mapper.mapToDto(applicationRepository.save(application));
        String message = "Checking job... Please try later.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @NewSpan("send-job-request")
    private void sendJob(String candidateId, String jobId) {
        JobRequest jobRequest = JobRequest.builder()
                .candidateId(candidateId)
                .jobId(jobId).build();
        kafkaTemplate.send(applicationTopic, jobRequest);
    }

    private String modifyApplication(Optional<Application> applicationOpt) {
        String message;
        if(applicationOpt.get().getStatus())
            message = "You have already applied in this job.";
        else {
            Application application = applicationOpt.get();
            application.setStatus(true);
            applicationRepository.save(application);
            message = "Job has been applied.";
        }
        return message;
    }

    @Override
    public List<ApplicationDto> findAll() {
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        List<Application> applications = applicationRepository.findAll();
        for (Application application: applications) {
            ApplicationDto applicationDto = mapper.mapToDto(application);
            applicationDtos.add(applicationDto);
        }
        return applicationDtos;
    }

    @Override
    public ApplicationDto findById(Integer id) {
        Optional<Application> applicationOpt = applicationRepository.findById("id");
        if(!applicationOpt.isPresent()){
            System.out.println("Application not found!");
            return null;
        }
        return mapper.mapToDto(applicationOpt.get());
    }

    @Override
    public ApplicationDto update(ApplicationDto applicationDto) {
        Optional<Application> applicationOpt = applicationRepository.findById(applicationDto.getId());
        if(!applicationOpt.isPresent()){
            System.out.println("Application not found!");
            return null;
        }
        Application application = mapper.mapToApplication(applicationDto);
        return mapper.mapToDto(application);
    }
}
