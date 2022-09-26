package com.swa.apply.dto;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ApplicationDto {

    private Integer id;
    private float resumeVersion;
    private Integer candidateId;
    private Integer jobId;
    private LocalDate date;
}
