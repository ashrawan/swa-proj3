package com.swa.proj3commonmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEmailDto {
    private String email;
    private String subject;
    private String message;
}
