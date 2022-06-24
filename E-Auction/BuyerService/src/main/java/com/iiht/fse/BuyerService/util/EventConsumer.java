package com.iiht.fse.BuyerService.util;

//import com.iiht.fse.BuyerService.avro.SellerDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventConsumer {

//    @KafkaListener(topics = "#{'${io.confluent.developer.config.topic.name}'}")
//    public void consume(final ConsumerRecord<Long, SellerDetails> consumerRecord) {
//        log.info("received {} {}", consumerRecord.key(), consumerRecord.value());
//    }
}
