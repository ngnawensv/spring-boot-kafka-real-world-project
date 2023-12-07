package com.belrose.kafka;

import com.belrose.model.WikimediaData;
import com.belrose.repository.WikimediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDatabaseConsumer {

    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(topics = "${spring.topic.name}", groupId = "${spring.consumer.group-id}")
    public void consumer(String eventMessage){
        log.info(String.format("KafkaDatabaseConsumer->consumer(): Message received =>%s", eventMessage));
        WikimediaData wikimediaData = WikimediaData.builder().wikiEventData(eventMessage).build();
        wikimediaDataRepository.save(wikimediaData);
    }
}
