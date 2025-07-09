//package com.example.solace;
//
//import org.apache.camel.ProducerTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import javax.jms.JMSException;
//import java.util.HashMap;
//
//@Service
//public class KafkaMessageListner {
//
//
//
//    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void listen(String message) throws JMSException {
//        // Custom processing logic
//        System.out.println("Received message: " + message);
//        // Here you can add your processing logic, e.g., saving to a database, etc.
//    }
//}
