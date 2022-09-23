package com.swa.recruiterservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDescriptionDto {
    private String jobDesignation;
    private String jobCompanyName;
    private String jobExperience;
    private String jobSalary;
    private String jobLocation;
}
