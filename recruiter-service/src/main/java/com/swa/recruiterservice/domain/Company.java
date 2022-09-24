package com.swa.recruiterservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String name;
    @ManyToMany(mappedBy = "companies")
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
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
