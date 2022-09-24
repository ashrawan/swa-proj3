package com.swa.recruiterservice;

import com.swa.recruiterservice.repository.CompanyRepository;
import com.swa.recruiterservice.repository.RecruiterRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class RecruiterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruiterServiceApplication.class, args);
    }

}
