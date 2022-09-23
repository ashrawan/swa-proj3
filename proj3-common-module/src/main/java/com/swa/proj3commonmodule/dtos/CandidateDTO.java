package com.swa.proj3commonmodule.dtos;

import lombok.Data;

@Data
public class CandidateDTO {

    private String candidateID;
    private String fullName;
    private String summary;
    private String skillDesc;
    private String address;
}
