package com.swa.candidateservice.service;

import com.swa.candidateservice.entity.Candidate;
import com.swa.candidateservice.repository.CandidateRepository;
import com.swa.proj3commonmodule.dto.CandidateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        Candidate saveCandidate = candidateRepository.save(candidate);
        candidateDTO.setCandidateID(saveCandidate.getCandidateID());
        return candidateDTO;
    }

    @Override
    public CandidateDTO findById(String id) {
        Candidate candidate = candidateRepository.findById(id).get();
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
                .build();
        return candidateDTO;
    }

    @Override
    public List<CandidateDTO> findAll() {
        List<Candidate> candidateList = candidateRepository.findAll();
        if (candidateList == null) {
            throw new RuntimeException("Candidates is Empty");
        }
        List<CandidateDTO> candidateDTOList = candidateList.stream()
                .map(candidate -> {
                    CandidateDTO candidateDTO = new CandidateDTO();
                    candidateDTO.setCandidateID(candidate.getCandidateID());
                    candidateDTO.setFullName(candidateDTO.getFullName());
                    candidateDTO.setSummary(candidate.getSummary());
                    candidateDTO.setAddress(candidateDTO.getAddress());
                    candidateDTO.setSkillDesc(candidate.getSkillDesc());
                    return candidateDTO;
                }).collect(Collectors.toList());
        return candidateDTOList;
    }
}
