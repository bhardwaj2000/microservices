package com.example.kafka.wikimedia.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaWikiConfig {

    @Bean
    public NewTopic kafkaWikiMediaTopic(){
        return TopicBuilder.name("WikiMediaTopic").build();
    }
}
