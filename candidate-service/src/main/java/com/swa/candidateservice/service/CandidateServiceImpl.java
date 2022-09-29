package com.swa.candidateservice.service;

import com.swa.candidateservice.entity.Candidate;
import com.swa.candidateservice.repository.CandidateRepository;
import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    @Value("${spring.kafka.custom.candidate-topic}")
    private String candidateTopic;

    @Value("${spring.kafka.custom.notification-topic}")
    private String notificationTopic;

    @Autowired
    private KafkaTemplate<String, EmailDto> kafkaEmailTemplate;

    @Autowired
    private KafkaTemplate<String, CandidateDTO> kafkaTemplate;
    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public CandidateDTO registerCandidate(CandidateDTO candidateDTO) {
        log.info("Candidate is Saving ...");
        Candidate candidate = Candidate.builder()
                .address(candidateDTO.getAddress())
                .fullName(candidateDTO.getFullName())
                .skillDesc(candidateDTO.getSkillDesc())
                .summary(candidateDTO.getSummary())
                .email(candidateDTO.getEmail())
                .build();
        Candidate saveCandidate = candidateRepository.save(candidate);
        candidateDTO.setCandidateID(saveCandidate.getCandidateID());
        kafkaTemplate.send(candidateTopic, candidateDTO);
        log.info("Candidate Save Successfully");

        EmailDto emailDto = new EmailDto();
        emailDto.setEmail(candidateDTO.getEmail());
        emailDto.setSubject("Profile Created");
        emailDto.setMessage("Hello \nYour Profile is created successfully.");
        log.info("Producing Email Object : {} ",emailDto);
        kafkaEmailTemplate.send(notificationTopic, emailDto);
        return candidateDTO;
    }

    @Override
    public CandidateDTO findById(String id) {
        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        Candidate candidate = candidateOpt.orElseThrow();
        if (candidate == null) {
            log.error("Candidate with id {} Not Found!!!",id);
            throw new RuntimeException("Candidate Not Found!!");
        }
        CandidateDTO candidateDTO = CandidateDTO.builder()
                .candidateID(candidate.getCandidateID())
                .summary(candidate.getSummary())
                .fullName(candidate.getFullName())
                .skillDesc(candidate.getSkillDesc())
                .address(candidate.getAddress())
                .email(candidate.getEmail())
                .build();
        log.info("Candidate Fetch Success with ID : ",id);
        return candidateDTO;
    }

    @Override
    public List<CandidateDTO> findAll() {
        List<Candidate> candidateList = candidateRepository.findAll();
        if (candidateList == null) {
            log.info("Candidate is Empty");
            throw new RuntimeException("Candidates is Empty");
        }
        List<CandidateDTO> candidateDTOList = candidateList.stream()
                .map(candidate -> {
                    CandidateDTO candidateDTO = new CandidateDTO();
                    candidateDTO.setCandidateID(candidate.getCandidateID());
                    candidateDTO.setFullName(candidate.getFullName());
                    candidateDTO.setSummary(candidate.getSummary());
                    candidateDTO.setAddress(candidate.getAddress());
                    candidateDTO.setSkillDesc(candidate.getSkillDesc());
                    candidateDTO.setEmail(candidate.getEmail());
                    return candidateDTO;
                }).collect(Collectors.toList());
        log.info("Candidate List Fetch Successfully");
        return candidateDTOList;
    }

}
