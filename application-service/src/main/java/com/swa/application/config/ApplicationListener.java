package com.swa.application.config;

import com.swa.application.model.Application;
import com.swa.application.repository.ApplicationRepository;
import com.swa.proj3commonmodule.response.ApplicationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationListener {

    @Autowired private ApplicationRepository applicationRepository;

    @KafkaListener(topics = "job_topic", groupId = "application-job-topic"
            , containerFactory = "kafkaListenerJsonFactory" //,autoStartup = "false"
    )
    void listener(ApplicationResponse response){
        log.info("----- ApplicationListener received ::: "+response.toString()+ " ;)");

        if(response.getHttpStatus() == HttpStatus.FOUND){
            Application application = applicationRepository.findByCandidateIdAndJobId(response.getCandidateId(), response.getJobId()).get();
            application.setStatus(false);
            applicationRepository.save(application);
        }

    }



}
