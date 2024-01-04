package com.belrose.kafka;

import com.belrose.config.KafkaConfigTopic;
import com.belrose.handler.wikimediaChangesHandler;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikimediaChangesProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private  final KafkaConfigTopic kafkaConfigTopic;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaConfigTopic kafkaConfigTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfigTopic = kafkaConfigTopic;
    }

    public  void sendMessage() throws InterruptedException {
        String topic = kafkaConfigTopic.getTopicName();

        //To read real time data from wikimedia, we use event source
        EventHandler eventHandler = new wikimediaChangesHandler(kafkaTemplate,topic);
        //Consume the endpoints to get the recent changes data from wikimedia: https://stream.wikimedia.org/v2/stream/recentchange
        String url =String.format("%s%s",kafkaConfigTopic.getWikimediaBaseUrl(),kafkaConfigTopic.getRecentChangeUri());
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
