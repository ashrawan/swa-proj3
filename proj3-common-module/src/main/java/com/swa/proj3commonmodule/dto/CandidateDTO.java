package com.swa.proj3commonmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {

    private String candidateID;
    private String fullName;
    private String email;
    private String summary;
    private String skillDesc;
    private String address;
}
