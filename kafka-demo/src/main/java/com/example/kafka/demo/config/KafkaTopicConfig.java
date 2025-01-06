package com.example.kafka.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

/*    @Bean
    public NewTopic kafkaDemoTopic(){
        return TopicBuilder.name("kafkaDemo").build();
    }

    @Bean
    public NewTopic kafkaDemoJsonTopic(){
        return TopicBuilder.name("kafkaDemoJson").build();
    }*/
}
