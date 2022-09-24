package com.swa.candidateservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Candidate {

    @Id
    private String candidateID;
    private String fullName;
    private String summary;
    private String skillDesc;
    private String address;
}
