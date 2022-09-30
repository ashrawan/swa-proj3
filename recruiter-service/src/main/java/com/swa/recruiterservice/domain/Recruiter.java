package com.swa.recruiterservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



//@Entity
@Builder
@Document(collection = "Recruiter")
@EqualsAndHashCode
public class Recruiter implements Serializable {

    @Id
    private String recruiterId;
    private String name;
    private String email;
    private String phone;
    private String recruiterCompany;

//    @ManyToMany(cascade = CascadeType.ALL)
    @DBRef
    private List<Company> companies = new ArrayList<>();

    public Recruiter(String recruiterId, String name, String email, String phone, String recruiterCompany, List<Company> companies) {
        this.recruiterId = recruiterId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.recruiterCompany = recruiterCompany;
        this.companies = companies;
    }

    public Recruiter() {
    }

    public String getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(String recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecruiterCompany() {
        return recruiterCompany;
    }

    public void setRecruiterCompany(String recruiterCompany) {
        this.recruiterCompany = recruiterCompany;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Recruiter{" +
                "recruiterId=" + recruiterId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", recruiterCompany='" + recruiterCompany + '\'' +
                '}';
    }
}