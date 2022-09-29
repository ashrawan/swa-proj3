package com.example.searchserviceelastic;

import com.example.searchserviceelastic.entity.JobEntity;
import com.example.searchserviceelastic.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class SearchServiceElasticApplication {

    @Autowired
    private JobService jobService;

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceElasticApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            JobEntity jobEntity = new JobEntity();
            jobEntity.setJobId(UUID.randomUUID().toString());
            jobEntity.setDescription("Test description");
            jobEntity.setTitle("Software Developer");
            jobService.save(jobEntity);
        };
    }
}
