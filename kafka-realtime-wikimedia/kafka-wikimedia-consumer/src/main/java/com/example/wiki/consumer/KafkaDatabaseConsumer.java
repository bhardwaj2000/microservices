package com.example.wiki.consumer;

import com.example.wiki.consumer.repository.WikimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Autowired
    private WikimediaRepository wikimediaRepository;

//    public WikimediaRepository getWikimediaRepository() {
//        return wikimediaRepository;
//    }

    @KafkaListener(topics = "WikiMediaTopic",
            groupId = "myGroup")
    public void consume(String message) {

        logger.info("Event message received -> {}", message);

        WikiMediaEntity wikiMediaEntity = new WikiMediaEntity();
        wikiMediaEntity.setWikiEventData(message);
        wikimediaRepository.save(wikiMediaEntity);
    }

}
