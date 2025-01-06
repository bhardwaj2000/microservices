package com.example.kafka.wikimedia.producer;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikiMediaChangeProducer {

    // create kafka tamplate object
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikiMediaChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "WikiMediaTopic";

        BackgroundEventHandler backgroundEventHandler = new WikiMediaEventHandler(kafkaTemplate,topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder uriBuilder = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(backgroundEventHandler, uriBuilder);
        BackgroundEventSource backgroundEventSource = builder.build();
        backgroundEventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }

}
