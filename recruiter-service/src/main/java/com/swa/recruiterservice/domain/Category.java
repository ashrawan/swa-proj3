package com.swa.recruiterservice.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryTitle;

    @OneToMany(mappedBy =  "category")
    private List<JobDescription> jobs = new ArrayList<>();


    public Category() {
        super();
    }



    public Category(String categoryTitle, List<JobDescription> jobs) {
        super();
        this.categoryTitle = categoryTitle;
        this.jobs = jobs;
    }



    public Category(int categoryId, String categoryTitle,List<JobDescription> jobs) {
        super();
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.jobs = jobs;
    }



    public int getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public String getCategoryTitle() {
        return categoryTitle;
    }


    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }



    public List<JobDescription> getJobs() {
        return jobs;
    }



    public void setJobs(List<JobDescription> jobs) {
        this.jobs = jobs;
    }



    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", jobs=" + jobs + "]";
    }



}