package com.swa.proj3commonmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecruiterEmailDto {
    private String email;
    private String subject;
    private String message;
}