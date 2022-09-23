package com.swa.recruiterservice.domain;


import javax.persistence.*;

@Entity
public class JobDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String jobDesignation;
    private String jobCompanyName;
    private String jobExperience;
    private String jobSalary;
    private String jobLocation;

    @ManyToOne
    private Category category;

    //constructor with no parameters
    public JobDescription() {
        super();
    }


    //constructor without id as parameter
    public JobDescription(String jobDesignation, String jobCompanyName, String jobExperience, String jobSalary,
                          String jobLocation, Category category) {
        super();
        this.jobDesignation = jobDesignation;
        this.jobCompanyName = jobCompanyName;
        this.jobExperience = jobExperience;
        this.jobSalary = jobSalary;
        this.jobLocation = jobLocation;
        this.category = category;
    }


    //constructor with all parameters
    public JobDescription(int jobId, String jobDesignation, String jobCompanyName, String jobExperience,
                          String jobSalary, String jobLocation, Category category) {
        super();
        this.jobId = jobId;
        this.jobDesignation = jobDesignation;
        this.jobCompanyName = jobCompanyName;
        this.jobExperience = jobExperience;
        this.jobSalary = jobSalary;
        this.jobLocation = jobLocation;
        this.category = category;
    }






    //getter and setter of all
    public int getJobId() {
        return jobId;
    }





    public void setJobId(int jobId) {
        this.jobId = jobId;
    }


    public String getJobDesignation() {
        return jobDesignation;
    }


    public void setJobDesignation(String jobDesignation) {
        this.jobDesignation = jobDesignation;
    }


    public String getJobExperience() {
        return jobExperience;
    }


    public void setJobExperience(String jobExperience) {
        this.jobExperience = jobExperience;
    }


    public String getJobSalary() {
        return jobSalary;
    }


    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }


    public String getJobLocation() {
        return jobLocation;
    }


    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }




    public String getJobCompanyName() {
        return jobCompanyName;
    }


    public void setJobCompanyName(String jobCompanyName) {
        this.jobCompanyName = jobCompanyName;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    //toString method
    @Override
    public String toString() {
        return "JobDescription [jobId=" + jobId + ", jobDesignation=" + jobDesignation + ", jobCompanyName="
                + jobCompanyName + ", jobExperience=" + jobExperience + ", jobSalary=" + jobSalary + ", jobLocation="
                + jobLocation + ", category=" + category + "]";
    }


}