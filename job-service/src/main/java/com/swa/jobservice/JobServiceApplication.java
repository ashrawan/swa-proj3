package com.swa.jobservice;

import com.swa.jobservice.service.JobService;
import com.swa.proj3commonmodule.dto.JobDTO;
import com.swa.proj3commonmodule.dto.JobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class JobServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, JobRequest> kt){
        return args -> {
            kt.send("application_topic",  JobRequest.builder().jobId("100").build());
        };
    }
}
