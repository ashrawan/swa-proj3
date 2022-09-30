package com.swa.recruiterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class RecruiterServiceApplication {

//    @Value("${kafka.topic}")
//    private String kafkatopic;

    public static void main(String[] args) {
        SpringApplication.run(RecruiterServiceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
//        return args -> {
//                kafkaTemplate.send(kafkatopic, "hello Kafka from recruiter events ");
////                kafkaTemplate.send(kafkatopic, 1, "key", "hello Kafka " +  "hello Kafka from recruiter events " );
//        };
//    }

}
