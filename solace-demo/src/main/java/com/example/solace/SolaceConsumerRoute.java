package com.example.solace;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SolaceConsumerRoute extends RouteBuilder {

    @Value("${solace.jms.queue}")
    private String solaceQueue;

    @Value("${input.queue.param}")
    private String inputQueueParam;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .log("Error occurred: ${exception.message}")
                .handled(true);
        String inQueue = solaceQueue+inputQueueParam;
        /*from(inQueue)
                .log("Received message: ${body}")
                .process(exchange -> {
                    // Custom processing logic
                    String message = exchange.getIn().getBody(String.class);
                    System.out.println("Processed message: " + message);
                });*/
        from("kafka:{{kafka.topic.name}}?brokers={{spring.kafka.bootstrap-servers}}" +
                "&groupId={{spring.kafka.consumer.group-id}}" +
                "&consumersCount=1")
                .routeId("kafka-to-solace-route")
                .log("Received message from Kafka: ${body}")
                .process(exchange -> {
                    // Custom processing logic
                    String message = exchange.getIn().getBody(String.class);
                    System.out.println("Processed message: " + message);
                })
                .to( inQueue); // Send to Solace queue
    }
}

