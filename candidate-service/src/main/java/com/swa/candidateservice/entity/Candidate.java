package com.swa.candidateservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Candidate")
public class Candidate {

    @Id
    private String candidateID;
    private String fullName;
    private String email;
    private String summary;
    private String skillDesc;
    private String address;
}
