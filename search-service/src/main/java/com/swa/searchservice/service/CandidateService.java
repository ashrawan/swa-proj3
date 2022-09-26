package com.swa.searchservice.service;

import com.swa.proj3commonmodule.dto.CandidateDTO;

import java.util.List;

public interface CandidateService {

    List<CandidateDTO> getAllCandidates();

    CandidateDTO getCandidateById(String candidateId);

    CandidateDTO saveCandidate(CandidateDTO candidateRequest);

    CandidateDTO updateCandidate(CandidateDTO candidateRequest);

}
