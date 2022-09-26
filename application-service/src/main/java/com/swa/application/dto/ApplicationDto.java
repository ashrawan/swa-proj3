package com.swa.application.dto;

import lombok.*;

import java.time.LocalDate;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ApplicationDto {

    private String id;
    private float resumeVersion;
    private String candidateId;
    private String jobId;
    private LocalDate date;
    private Boolean status;
}
