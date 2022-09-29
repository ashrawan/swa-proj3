package com.swa.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
//@EnableFeignClients
public class ApplicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationServiceApplication.class, args);
    }


//    @Bean
//    CommandLineRunner clr(KafkaTemplate<Object, JobResponse> kt){
//        return args -> {
//            JobResponse jr =  JobResponse.builder().message("Application - send message to job_topic").build();
//            kt.send("job_topic", jr);
//        };
//    }
}
