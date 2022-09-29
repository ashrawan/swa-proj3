package com.swa.candidateservice.mapper;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.candidateservice.entity.Candidate;

public class Mapper {

    public CandidateDTO mappedToCandidateDTO(Candidate candidate) {
        CandidateDTO candidateDTO = CandidateDTO.builder()
                .candidateID(candidate.getCandidateID())
                .email(candidate.getEmail())
                .address(candidate.getAddress())
                .fullName(candidate.getFullName())
                .skillDesc(candidate.getSkillDesc())
                .summary(candidate.getSummary())
                .build();
        return candidateDTO;
    }

    public Candidate mappedToCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = Candidate.builder()
                .candidateID(candidateDTO.getCandidateID())
                .email(candidateDTO.getEmail())
                .address(candidateDTO.getAddress())
                .fullName(candidateDTO.getFullName())
                .skillDesc(candidateDTO.getSkillDesc())
                .summary(candidateDTO.getSummary())
                .build();
        return candidate;
    }
}
