package com.example.searchserviceelastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchServiceElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceElasticApplication.class, args);
    }


//    @Bean
//    CommandLineRunner commandLineRunner(JobService jobService) {
//        return args -> {
//            List<JobEntity> list = jobService.findAllByTitle("software developer");
//            list.forEach(jobEntity -> System.out.println(jobEntity));
//        };
//    }
}
