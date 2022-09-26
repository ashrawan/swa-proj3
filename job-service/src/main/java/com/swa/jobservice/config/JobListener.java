package com.swa.jobservice.config;

import com.swa.jobservice.service.JobService;
import com.swa.proj3commonmodule.dto.JobRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobListener {

    @Autowired private JobService jobService;

    @KafkaListener(topics = "application_topic", groupId="job-application-topic"
            , containerFactory = "kafkaListenerJsonFactory" //,autoStartup = "false"
    )
    void listener(JobRequest dto){
        log.info("----- JobListener received ::: "+dto.toString()+ " ;)");
        jobService.existJob(dto);

    }

}
