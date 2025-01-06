package com.example.kafka.demo.kafka;

import com.example.kafka.demo.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "kafkaDemoJson", groupId = "myGroup")
    public void consumer(User user){
        log.info("message received in json -> {}",user.toString());
    }
}
