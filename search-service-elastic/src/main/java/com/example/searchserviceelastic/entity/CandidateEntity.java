package com.example.searchserviceelastic.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
public class CandidateEntity {

    @Id
    private String candidateId;

    private String fullName;

    private String summary;

    private String address;

    private Set<String> skills = new HashSet<>();
}
