package com.example.searchserviceelastic.service;

import com.example.searchserviceelastic.entity.CandidateEntity;

import java.util.List;

public interface CandidateService {
    List<CandidateEntity> findAllByName(String candidateName);

    CandidateEntity findOne(String id);

    CandidateEntity save(CandidateEntity candidateEntity);

    CandidateEntity update(CandidateEntity candidateEntity);

    void deleteById(String id);
}
