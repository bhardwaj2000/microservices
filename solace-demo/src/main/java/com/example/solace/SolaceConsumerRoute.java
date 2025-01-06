package com.example.solace;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SolaceConsumerRoute extends RouteBuilder {

    @Value("${solace.jms.queue}")
    private String solaceQueue;

    @Override
    public void configure() throws Exception {
        from("jms:queue:" + solaceQueue)
                .log("Received message: ${body}")
                .process(exchange -> {
                    // Custom processing logic
                    String message = exchange.getIn().getBody(String.class);
                    System.out.println("Processed message: " + message);
                });
    }
}

