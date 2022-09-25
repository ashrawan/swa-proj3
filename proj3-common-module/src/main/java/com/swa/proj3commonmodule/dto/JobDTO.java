package com.swa.proj3commonmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private String jobId;
    private String title;
    private String description;
    private String position;
    private String category;
    private List<String> skills;
}
