//package com.swa.recruiterservice.listener;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaListeners {
//
////    @KafkaListener(topics = {"${spring.kafka.custom.candidate-topic}"}, containerFactory = "kafkaListenerJsonFactory",
////            groupId = "${spring.kafka.consumer.group-id}", autoStartup = "${spring.kafka.custom.enable-listeners}")
//    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
//    void listener(String data){
//        System.out.println("Received listener  " + data + " :) ");
//    }
//}
