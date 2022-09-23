package com.swa.recruiterservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recruiterId;
    private String recruiterName;
    private String recruiterEmail;
    private String recruiterContact;
    private String recruiterCompanyName;

    //no parameter constructor
    public Recruiter() {
        super();
    }


    //constructor without id
    public Recruiter(String recruiterName, String recuiterEmail, String recuiterContact,
                     String recuiterCompanyName) {
        super();
        this.recruiterName = recruiterName;
        this.recruiterEmail = recuiterEmail;
        this.recruiterContact = recuiterContact;
        this.recruiterCompanyName = recuiterCompanyName;
    }


    //constructor of all attributes
    public Recruiter(int recruiterId, String recruiterName, String recuiterEmail,
                     String recuiterContact, String recuiterCompanyName) {
        super();
        this.recruiterId = recruiterId;
        this.recruiterName = recruiterName;
        this.recruiterEmail = recuiterEmail;
        this.recruiterContact = recuiterContact;
        this.recruiterCompanyName = recuiterCompanyName;
    }


    //getter and setter
    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }


    public String getRecruiterEmail() {
        return recruiterEmail;
    }

    public void setRecruiterEmail(String recuiterEmail) {
        this.recruiterEmail = recuiterEmail;
    }

    public String getRecruiterContact() {
        return recruiterContact;
    }

    public void setRecruiterContact(String recuiterContact) {
        this.recruiterContact = recuiterContact;
    }

    public String getRecruiterCompanyName() {
        return recruiterCompanyName;
    }

    public void setRecruiterCompanyName(String recuiterCompanyName) {
        this.recruiterCompanyName = recuiterCompanyName;
    }

    //toString
    @Override
    public String toString() {
        return "Recruiter [recruiterId=" + recruiterId + ", recruiterName=" + recruiterName + ", recuiterPassword="
                 + ", recuiterEmail=" + recruiterEmail + ", recuiterContact=" + recruiterContact
                + ", recuiterCompanyName=" + recruiterCompanyName + "]";
    }

}