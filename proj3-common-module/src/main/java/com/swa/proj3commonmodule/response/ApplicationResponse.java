package com.swa.proj3commonmodule.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class JobResponse {
    private String id;
    private HttpStatus httpStatus;
    private String message;

}
