package com.swa.searchservice;

import com.swa.proj3commonmodule.dto.CandidateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"${spring.kafka.values.candidate-topic}"}, containerFactory = "kafkaListenerJsonFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeSuperHero(CandidateDTO candidateDTO) {
        logger.info("Received CandidateDTO {}", candidateDTO);
    }

}