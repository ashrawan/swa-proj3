package com.swa.searchservice.service;

import com.swa.proj3commonmodule.dto.CandidateDTO;

public interface SearchService {

    CandidateDTO getCandidateById(String candidateId);
}
