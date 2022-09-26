package com.swa.searchservice;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = {"${spring.kafka.custom.candidate-topic}"}, containerFactory = "kafkaListenerJsonFactory",
            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void consumeSuperHero(CandidateDTO candidateDTO) {
        log.info("Received CandidateDTO {}", candidateDTO);

    }

}