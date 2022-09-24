package com.swa.candidateservice.service;

import com.swa.proj3commonmodule.dto.CandidateDTO;

import java.util.List;

public interface CandidateService {

    CandidateDTO registerCandidate(CandidateDTO candidateDTO);

    CandidateDTO findById(String id);

    List<CandidateDTO> findAll();

}
