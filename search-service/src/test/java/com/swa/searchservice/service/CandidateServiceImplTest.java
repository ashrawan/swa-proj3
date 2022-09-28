package com.swa.searchservice.service;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.exceptions.NotFoundException;
import com.swa.searchservice.entity.CandidateTable;
import com.swa.searchservice.helper.mapper.CandidateMapper;
import com.swa.searchservice.repository.CandidateRepository;
import com.swa.searchservice.service.impl.CandidateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    private CandidateMapper candidateMapper;

    private CandidateServiceImpl candidateService;

    private CandidateDTO candidateDTO;

    private String uuid = UUID.randomUUID().toString();

    @BeforeEach()
    public void setUp() {
        candidateMapper = new CandidateMapper();
        candidateService = new CandidateServiceImpl(candidateRepository, candidateMapper);
        candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateID(uuid);
        candidateDTO.setFullName("Test Candidate");
    }

    @Test
    public void testGetAllCandidates_thenResultCandidateList() {

        List<CandidateTable> candidateTableList = new ArrayList<>();
        when(candidateRepository.findAll()).thenReturn(candidateTableList);
        assertEquals(candidateService.getAllCandidates(), candidateTableList);
    }

    @Test
    public void testGetCandidateById_ResultCandidate() {

        CandidateTable candidateTable = candidateMapper.toEntity(candidateDTO);
        UUID candidateUUID = UUID.fromString(candidateDTO.getCandidateID());
        when(candidateRepository.findById(candidateUUID)).thenReturn(Optional.ofNullable(candidateTable));
        Assertions.assertEquals(candidateDTO, candidateService.getCandidateById(candidateDTO.getCandidateID()));
    }

    @Test
    public void testSaveCandidate_thenResultCandidate() {

        CandidateTable candidateTable = candidateMapper.toEntity(candidateDTO);
        when(candidateRepository.save(candidateTable)).thenReturn(candidateTable);
        Assertions.assertEquals(candidateService.saveCandidate(candidateDTO), candidateDTO);
    }

    @Test
    public void testGetByCandidateIdWhichIsNotAvailable_thenExpectedException() {

        CandidateTable savedCandidateData = candidateMapper.toEntity(candidateDTO);
        UUID candidateUUID = UUID.fromString(candidateDTO.getCandidateID());
        lenient().when(candidateRepository.findById(candidateUUID)).thenReturn(Optional.ofNullable(savedCandidateData));

        Assertions.assertThrows(NotFoundException.class, () -> {
            candidateService.getCandidateById(UUID.randomUUID().toString());
        });
    }

    @Test
    public void testUpdateCandidate_thenUpdatedCandidate() {

        CandidateTable savedCandidateData = candidateMapper.toEntity(candidateDTO);
        UUID candidateUUID = UUID.fromString(candidateDTO.getCandidateID());
        when(candidateRepository.findById(candidateUUID)).thenReturn(Optional.of(savedCandidateData));
        when(candidateRepository.save(savedCandidateData)).thenReturn(savedCandidateData);
        Assertions.assertEquals(candidateService.updateCandidate(candidateDTO), candidateDTO);
    }

}
