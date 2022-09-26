package com.swa.proj3commonmodule.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobKafkaDto {

    private String jobId;
    private String candidateId;
}

