package com.swa.application.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ServerResponse {

    private int statusCode;
    private String message;
}
