package com.iiht.fse.BuyerService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * **TODO
 * Configure all the beans as described below to send messages to Kafka Message broker.
 * Add appropriate annotation to this Bean Configuration class
 */
@Configuration
public class KafkaConfig {

    // injected from application.properties
    @Value("${io.confluent.developer.config.topic.name}")
    private String topicName;

    @Value("${io.confluent.developer.config.topic.partitions}")
    private int numPartitions;

    @Value("${io.confluent.developer.config.topic.replicas}")
    private int replicas;

    @Bean
    NewTopic sellerProductsTopic() {
        return new NewTopic(topicName, numPartitions, (short) replicas);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
