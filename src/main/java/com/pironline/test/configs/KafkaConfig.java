package com.pironline.test.configs;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.groupId}")
    private String groupId;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${spring.kafka.consumer.partition.assignment.strategy}")
    private String partitionAssignmentStrategy;

    @Bean
    public ConsumerFactory<String, String> pironlineConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, partitionAssignmentStrategy);
        return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        BackOff fixedBackOff = new FixedBackOff(10, 1);
        return new DefaultErrorHandler((consumerRecord, exception) -> {}, fixedBackOff);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> pironlineListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pironlineConsumerFactory());
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }
}
