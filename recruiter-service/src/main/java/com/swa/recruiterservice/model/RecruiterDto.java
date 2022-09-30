package com.swa.recruiterservice.model;

import com.swa.recruiterservice.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruiterDto {

    private String name;
    private String email;
    private String phone;
    private String recruiterCompany;
    private List<Company> companies = new ArrayList<>();

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
        return new ArrayList<>(this.companies);
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}