package com.pironline.test.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Value("${spring.kafka.topic}")
    private String topic;

    @KafkaListener(
            topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.groupId}",
            autoStartup = "${spring.kafka.consumer.listener.enabled}",
            concurrency = "${spring.kafka.consumer.listener.concurrency}",
            containerFactory = "pironlineListenerContainerFactory"
    )
    public void messageListener(String data) {
        System.out.println("Topic - " + topic + ", Data - " + data);
    }
}
