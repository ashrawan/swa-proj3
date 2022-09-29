package com.example.searchserviceelastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class SearchServiceElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceElasticApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(JobService jobService) {
//        return args -> {
//            JobEntity jobEntity = new JobEntity();
//            jobEntity.setJobId(UUID.randomUUID().toString());
//            jobEntity.setDescription("Test description");
//            jobEntity.setTitle("Software Developer");
//            jobService.save(jobEntity);
//        };
//    }
}
