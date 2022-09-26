package com.swa.candidateservice;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExampleJobService {

    @Value("${spring.kafka.custom.candidate-topic}")
    private String candidateTopic;

    @Autowired
    private KafkaTemplate<String, CandidateDTO> kafkaCandidateTemplate;


//    Disabling Kafka Producer For development, to enable uncomment @Bean
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            CandidateDTO candidateDTO = new CandidateDTO();
            candidateDTO.setCandidateID(UUID.randomUUID().toString());
            candidateDTO.setFullName("Test Candidate");
            candidateDTO.setSummary("A passionate Software developer, having skills in Full Stack Development");
            candidateDTO.setSkillDesc("Java, Spring, Kafka");
            kafkaCandidateTemplate.send(candidateTopic, candidateDTO);
        };
    }
}
