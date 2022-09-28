package com.swa.searchservice.service.impl;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.exceptions.NotFoundException;
import com.swa.searchservice.entity.CandidateTable;
import com.swa.searchservice.helper.mapper.CandidateMapper;
import com.swa.searchservice.repository.CandidateRepository;
import com.swa.searchservice.service.CandidateService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final CandidateMapper candidateMapper;


    @Cacheable(value = "candidates", key = "'all'", condition = "#result.size() > 0")
    @Override
    public List<CandidateDTO> getAllCandidates() {
        List<CandidateTable> candidateTableList = candidateRepository.findAll();
        return candidateMapper.toDtoList(candidateTableList);
    }

    @Cacheable(value = "candidates", key = "#candidateId")
    @Override
    public CandidateDTO getCandidateById(String candidateId) {
        log.info("Get candidate From DB with ID: " + candidateId);
        UUID uuid = UUID.fromString(candidateId);
        CandidateTable foundCandidate = candidateRepository.findById(uuid).orElseThrow(() -> new NotFoundException());
        return candidateMapper.toDto(foundCandidate);
    }

    @CachePut(value = "candidates", condition = "#result.candidateID != null", key = "#result.candidateID")
    @CacheEvict(value = "candidates", key = "'all'")
    @Override
    public CandidateDTO saveCandidate(CandidateDTO candidateRequest) {
        CandidateTable candidateTable = candidateMapper.toEntity(candidateRequest);
        CandidateTable savedCandidate = candidateRepository.save(candidateTable);
        return candidateMapper.toDto(savedCandidate);
    }

    @CachePut(value = "candidates", key = "#candidateTable.candidateId")
    @CacheEvict(value = "candidates", key = "'all'")
    @Override
    public CandidateDTO updateCandidate(CandidateDTO candidateRequest) {
        CandidateTable foundCandidate = candidateRepository.findById(UUID.fromString(candidateRequest.getCandidateID()))
                .orElseThrow(() -> new NotFoundException());
        foundCandidate.setFullName(candidateRequest.getFullName());
        foundCandidate.setAddress(candidateRequest.getAddress());
        foundCandidate.setSummary(candidateRequest.getSummary());
        candidateRepository.save(foundCandidate);
        return candidateMapper.toDto(foundCandidate);
    }

}
