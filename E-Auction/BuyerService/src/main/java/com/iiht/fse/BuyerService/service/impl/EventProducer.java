package com.iiht.fse.BuyerService.service.impl;

import com.iiht.fse.BuyerService.avro.BidDetails;
import com.iiht.fse.BuyerService.model.BuyerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@CommonsLog(topic = "Product Logger")
public class EventProducer {

    @Value("${io.confluent.developer.config.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, BidDetails> kafkaTemplate;

    void sendMessage(BidDetails bidDetails) {
        this.kafkaTemplate.send(this.topicName, bidDetails.getId(), bidDetails)
                .addCallback(result -> {
                            final RecordMetadata m;
                            if (result != null) {
                                m = result.getRecordMetadata();
                                log.info("Produced record to topic {} partition {} @ offset {}",
                                        m.topic(),
                                        m.partition(),
                                        m.offset());
                            }
                        },
                        exception -> log.error("Failed to produce to kafka", exception));
        kafkaTemplate.flush();
        log.info(String.format("Produced bidDetails -> %s", bidDetails));
    }

}
