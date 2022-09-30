package com.example.searchserviceelastic.service;

import com.example.searchserviceelastic.entity.CandidateEntity;
import com.example.searchserviceelastic.entity.JobEntity;
import com.example.searchserviceelastic.mapper.CandidateMapper;
import com.example.searchserviceelastic.mapper.JobMapper;
import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.JobDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final CandidateMapper candidateMapper;
    private final JobMapper jobMapper;

    private final JobService jobService;
    private final CandidateService candidateService;

    @KafkaListener(topics = {"${spring.kafka.custom.job-topic}"}, containerFactory = "kafkaListenerJsonFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeJobDTO(JobDTO jobDTO) {
        log.info("Received JobDTO {}", jobDTO);
        JobEntity jobEntity = jobMapper.toEntity(jobDTO);
        JobEntity savedJob = jobService.save(jobEntity);
        log.info("Successfully Saved Job {}", savedJob);
    }

    @KafkaListener(topics = {"${spring.kafka.custom.candidate-topic}"}, containerFactory = "kafkaListenerJsonFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeCandidateDTO(CandidateDTO candidateDTO) {
        log.info("Received CandidateDTO {}", candidateDTO);
        CandidateEntity candidateEntity = candidateMapper.toEntity(candidateDTO);
        CandidateEntity savedCandidate = candidateService.save(candidateEntity);
        log.info("Successfully Saved Candidate {}", savedCandidate);
    }

}
