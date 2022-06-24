package com.iiht.fse.SellerService.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.fse.BuyerService.avro.BidDetails;
import com.iiht.fse.SellerService.model.Bid;
import com.iiht.fse.SellerService.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventConsumer {

    private final BidRepository bidRepository;

    private final ModelMapper modelMapper;

    @KafkaListener(topics = "#{'${io.confluent.developer.config.topic.name}'}")
    public void consume(final ConsumerRecord<Long, BidDetails> consumerRecord) throws JsonProcessingException {
        log.info("received bid details from topic: {} {}", consumerRecord.key(), consumerRecord.value());
        BidDetails bidDetails = (BidDetails) consumerRecord.value();
        Bid bid = modelMapper.map(bidDetails, Bid.class);
    //    log.info("converted bid object : {}", new ObjectMapper().writeValueAsString(bid));
        bidRepository.save(bid);
    }
}
