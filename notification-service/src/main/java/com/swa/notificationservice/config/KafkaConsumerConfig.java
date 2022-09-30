package com.swa.notificationservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private Map<String, Object> consumerJsonConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerJsonFactory() {

        ConcurrentKafkaListenerContainerFactory<String, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        DefaultKafkaConsumerFactory<String, Object> defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerJsonConfig());

        final JsonDeserializer<Object> valueDeserializer = new JsonDeserializer<>();
        valueDeserializer.addTrustedPackages("com.swa.proj3commonmodule.dto");
        defaultKafkaConsumerFactory.setValueDeserializer(valueDeserializer);
        defaultKafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());

        factory.setConsumerFactory(defaultKafkaConsumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public KafkaListenerErrorHandler kafkaListenerErrorHandler() {
        return (m,e) -> {
            log.error("Got and error {} ",e.getMessage());
            return e;
        };
    }
}
