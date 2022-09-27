package com.example.searchserviceelastic.mapper;


import com.example.searchserviceelastic.entity.CandidateEntity;
import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.GenericMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CandidateMapper implements GenericMapper<CandidateDTO, CandidateEntity> {

    @Override
    public CandidateDTO toDto(CandidateEntity candidateEntity) {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateID(candidateEntity.getCandidateId());
        candidateDTO.setSummary(candidateEntity.getSummary());
        candidateDTO.setFullName(candidateEntity.getFullName());
        candidateDTO.setAddress(candidateEntity.getAddress());
        if (!ObjectUtils.isEmpty(candidateEntity.getSkills())) {
            candidateDTO.setSkillDesc(candidateEntity.getSkills().stream().collect(Collectors.joining()));
        }
        return candidateDTO;
    }

    @Override
    public CandidateEntity toEntity(CandidateDTO candidateDTO) {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setCandidateId(candidateDTO.getCandidateID());
        candidateEntity.setFullName(candidateDTO.getFullName());
        candidateEntity.setSummary(candidateDTO.getSummary());
        candidateEntity.setAddress(candidateDTO.getAddress());
        if (StringUtils.hasText(candidateDTO.getSkillDesc())) {
            Set<String> skillsDesc = Arrays.stream(candidateDTO.getSkillDesc().split(",")).collect(Collectors.toSet());
            candidateEntity.setSkills(skillsDesc);
        }
        return candidateEntity;
    }


}

