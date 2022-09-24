package com.swa.candidateservice.service;

import com.swa.candidateservice.entity.Candidate;
import com.swa.candidateservice.repository.CandidateRepository;
import com.swa.proj3commonmodule.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public CandidateDTO registerCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = Candidate.builder()
                .address(candidateDTO.getAddress())
                .fullName(candidateDTO.getFullName())
                .skillDesc(candidateDTO.getSkillDesc())
                .summary(candidateDTO.getSummary())
                .build();
        candidateRepository.save(candidate);
        return candidateDTO;
    }
}
