//package com.swa.recruiterservice.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//public class KafkaTopicConfig {
//
//    @Value("${kafka.topic}")
//    private String kafkatopic;
//
//    @Value("${kafka.partitions}")
//    private int partitions;
//
//    @Value("${kafka.replication-factor}")
//    private int replicationFactor;
//
//    @Bean
//    public NewTopic javaTestTopic(){
//        return TopicBuilder.name(kafkatopic).replicas(replicationFactor).partitions(partitions).build();
//    }
//}
