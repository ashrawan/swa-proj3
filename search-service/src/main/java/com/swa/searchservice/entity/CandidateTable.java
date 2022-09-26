package com.swa.searchservice.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(value = "Candidate_Table")
@Data
public class CandidateTable {
    @PrimaryKey
    private UUID candidateId;

    private String fullName;

    private String summary;

    private String address;

    private Set<String> skills = new HashSet<>();
}
