package com.swa.application.config;

import com.swa.proj3commonmodule.response.JobResponse;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener {

    private JobResponse jobResponse;
    private boolean flag = false;

    @KafkaListener(topics = "job_topic", groupId="application-job-topic")
    void listener(JobResponse jobResponse){
        flag = true;
        System.out.println("Listener received ::: "+jobResponse.toString()+ " ;)");
        this.jobResponse = jobResponse;
    }

    public JobResponse getJobResponse(){
        JobResponse sendResponse = null;
        if(flag){
            sendResponse = jobResponse;
            flag = false;
        } else {
            sendResponse = new JobResponse();
            sendResponse.setHttpStatus(HttpStatus.PROCESSING);
            sendResponse.setMessage("The server is waiting for a response. Please try again later!!");
        }

        return sendResponse;
    }



}
