package com.example.solaceToDB.SolaceToDB;

import org.apache.camel.BeanScope;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SolaceToDBRouter extends RouteBuilder {

    @Value("${mosaic.ocn.solace.topic}")
    private String solaceTopic;
    @Override
    public void configure() throws Exception {
        from(solaceTopic)
                .log("body .........${body}")
                .bean(ProcessService.class,"processMessage", BeanScope.Prototype);

    }
}
