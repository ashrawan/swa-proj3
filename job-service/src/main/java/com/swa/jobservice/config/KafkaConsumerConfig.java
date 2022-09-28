//package com.swa.jobservice.config;
//
//import com.swa.proj3commonmodule.dto.JobRequest;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.KafkaListenerErrorHandler;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Value("${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapServer;
//
//    @Value("job-application-topic")
//    private String groupId;
//
//    public Map<String, Object> consumerJsonConfig() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, JobRequest> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerJsonConfig(), new StringDeserializer(), new JsonDeserializer<>(JobRequest.class));
//    }
//
//    @Bean
//    public <T> ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerJsonFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, JobRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setMessageConverter(new StringJsonMessageConverter());
//        factory.setBatchListener(true);
//        return factory;
//    }
//
//
//    @Bean
//    public KafkaListenerErrorHandler kafkaListenerErrorHandler() {
//        return (m, e) -> {
////            logger.error("Got an error {}", e.getMessage());
//            return e;
//        };
//    }
//
//}
