package com.swa.recruiterservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruiterDto {

    private String recruiterName;
    private String recuiterEmail;
    private String recuiterContact;
    private String recuiterCompanyName;

}