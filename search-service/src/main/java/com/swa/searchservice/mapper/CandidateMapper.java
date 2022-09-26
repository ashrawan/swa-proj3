package com.swa.searchservice.mapper;


import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.GenericMapper;
import com.swa.searchservice.entity.CandidateTable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CandidateMapper implements GenericMapper<CandidateDTO, CandidateTable> {

    @Override
    public CandidateDTO toDto(CandidateTable candidateTable) {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateID(candidateTable.getCandidateId().toString());
        candidateDTO.setSummary(candidateTable.getSummary());
        candidateDTO.setFullName(candidateTable.getFullName());
        candidateDTO.setAddress(candidateTable.getAddress());
        candidateDTO.setSkillDesc(candidateTable.getSkills().stream().collect(Collectors.joining()));
        return candidateDTO;
    }

    @Override
    public CandidateTable toEntity(CandidateDTO candidateDTO) {
        CandidateTable candidateTable = new CandidateTable();
        candidateTable.setCandidateId(UUID.fromString(candidateDTO.getCandidateID()));
        candidateTable.setFullName(candidateDTO.getFullName());
        candidateTable.setSummary(candidateDTO.getSummary());
        candidateTable.setAddress(candidateDTO.getAddress());
        Set<String> skillsDesc = Arrays.stream(candidateDTO.getSkillDesc().split(",")).collect(Collectors.toSet());
        candidateTable.setSkills(skillsDesc);
        return candidateTable;
    }


}

