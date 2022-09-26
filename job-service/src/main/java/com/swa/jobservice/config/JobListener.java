package com.swa.jobservice.config;

import com.swa.jobservice.service.JobService;
import com.swa.proj3commonmodule.dto.JobKafkaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListener {

    @Autowired private JobService jobService;

    @KafkaListener(topics = "application_topic", groupId="job-application-topic")
    void listener(JobKafkaDto jobKafkaDto){
        System.out.println("Listener received ::: "+jobKafkaDto.toString()+ " ;)");

        jobService.existJob(jobKafkaDto);

    }

}

//@Component
//public class KafkaListeners {
//
//    @KafkaListener(topics = "job_topic", groupId="mygroupId")
//    void listener(String data){
//        System.out.println("Listener received ::: "+data+ " ;)");
//    }
//}