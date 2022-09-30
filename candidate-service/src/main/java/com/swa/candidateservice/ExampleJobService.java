package com.swa.candidateservice;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import com.swa.proj3commonmodule.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ExampleJobService {

    @Value("${spring.kafka.custom.candidate-topic}")
    private String candidateTopic;

    @Value("${spring.kafka.custom.notification-topic}")
    private String notificationTopic;

    @Autowired
    private KafkaTemplate<String, CandidateDTO> kafkaCandidateTemplate;
    @Autowired
    private KafkaTemplate<String, EmailDto> kafkaEmailTemplate;


//    Disabling Kafka Producer For development, to enable uncomment @Bean
//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> {
//            CandidateDTO candidateDTO = new CandidateDTO();
//            candidateDTO.setCandidateID(UUID.randomUUID().toString());
//            candidateDTO.setFullName("Test Candidate");
//            candidateDTO.setSummary("A passionate Software developer, having skills in Full Stack Development");
//            candidateDTO.setSkillDesc("Java, Spring, Kafka");
//            log.info("Producing object {}", candidateDTO);
//            kafkaCandidateTemplate.send(candidateTopic, candidateDTO);
//
//            EmailDto emailDto = new EmailDto();
//            emailDto.setEmail("testmailswa@gmail.com");
//            emailDto.setSubject("Test Mail");
//            emailDto.setMessage("Hello World !");
//            log.info("Producing Email Object : {} ",emailDto);
//            kafkaEmailTemplate.send(notificationTopic, emailDto);
//        };
//    }
}
