package com.swa.proj3commonmodule.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateDTO {

    private String candidateID;
    private String fullName;
    private String summary;
    private String skillDesc;
    private String address;
}
