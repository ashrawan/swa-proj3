package com.swa.proj3commonmodule.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ApplicationResponse {
    private String jobId;
    private String candidateId;
    private HttpStatus httpStatus;
    private String message;

}
