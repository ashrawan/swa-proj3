package com.swa.searchservice.service;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SearchServiceImpl implements SearchService {

    @Cacheable(value = "candidates", key = "#candidateId")
    @Override
    public CandidateDTO getCandidateById(String candidateId) {
        System.out.println("Getting candidate From DB with ID: " + candidateId);
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateID(UUID.randomUUID().toString());
        candidateDTO.setFullName("Candidate Test");
        candidateDTO.setSummary("Summary, This candidate will return unique id each time, if its not from cache.");
        return candidateDTO;
    }
}
