package com.pironline.test.kafka;

import com.pironline.test.dto.DebeziumDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Value("${spring.kafka.topic.config}")
    private String topic;

    @KafkaListener(
            topics = "${spring.kafka.topic.config}",
            groupId = "${spring.kafka.groupId}",
            autoStartup = "${spring.kafka.consumer.listener.enabled}",
            concurrency = "${spring.kafka.listener.concurrency}",
            containerFactory = "pironlineConsumerFactory"
    )
    public void messageListener(DebeziumDto debeziumDto) {
        System.out.println("Topic - "+ topic + ", DebeziumDto - " + debeziumDto);
    }
}
