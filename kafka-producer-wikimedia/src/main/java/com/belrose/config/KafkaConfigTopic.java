package com.belrose.config;

import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Data
public class KafkaConfigTopic {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${wikimedia.api.base}")
    private String wikimediaBaseUrl;
    @Value("${wikimedia.api.uri.recentchange}")
    private String recentChangeUri;

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(this.getTopicName())
               // .partitions(10)
                .build();
    }
}
