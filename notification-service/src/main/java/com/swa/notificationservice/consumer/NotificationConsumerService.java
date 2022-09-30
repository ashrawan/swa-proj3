package com.swa.notificationservice.consumer;

import com.swa.notificationservice.service.EmailSenderService;
import com.swa.proj3commonmodule.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumerService {

    @Autowired
    private EmailSenderService emailSenderService;

    @KafkaListener(topics = {"${spring.kafka.custom.notification-topic}"}, containerFactory = "kafkaListenerJsonFactory",
    groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
    public void receiveMessage(EmailDto emailDto) {
        emailSenderService.sendMail(emailDto);
        log.info("Consumer Email info {} ",emailDto);
    }
}
