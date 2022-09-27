package com.swa.recruiterservice;

import com.swa.recruiterservice.repository.CompanyRepository;
import com.swa.recruiterservice.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class RecruiterServiceApplication {

    @Value("${kafka.topic}")
    private String kafkatopic;

    public static void main(String[] args) {
        SpringApplication.run(RecruiterServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
        return args -> {
            for (int i = 0; i < 100; i++) {
//                kafkaTemplate.send(kafkatopic, "hello Kafka " + i);
//                kafkaTemplate.send("javaTopic", 1, "key", "hello Kafka " +  "test " + i);
            }
        };
    }

}
