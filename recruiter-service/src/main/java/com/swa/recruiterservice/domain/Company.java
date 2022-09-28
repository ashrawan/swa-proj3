package com.swa.recruiterservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//@Entity
@Document(collection = "Company")
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Company implements Serializable {

    @Id
    private String companyId;

    private String name;
//    @ManyToMany(mappedBy = "companies")
    @DBRef
    private List<Recruiter> recruiters = new ArrayList<>();

    public Company(String name, List<Recruiter> recruiters) {

        this.name = name;
        this.recruiters = recruiters;
    }

    public Company(String name) {
        this.name = name;
    }

    public Company() {
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Recruiter> getRecruiters() {
        return recruiters;
    }

    @JsonIgnore
    public void setRecruiters(List<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                '}';
    }
}
