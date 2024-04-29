package com.pironline.test.configs;

import com.pironline.test.dto.DebeziumDto;
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
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    private static final String TRUSTED_PACKAGES = "com.pironline.test.dto";
    private static final String VALUE_DEFAULT_TYPE = "com.pironline.test.dto.DebeziumDto";

    @Value("${spring.kafka.groupId}")
    private String groupId;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, DebeziumDto> pironlineConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        properties.put(JsonDeserializer.TRUSTED_PACKAGES, TRUSTED_PACKAGES);
        properties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, VALUE_DEFAULT_TYPE);
        ErrorHandlingDeserializer<DebeziumDto> errorHandlingDeserializer = new ErrorHandlingDeserializer(new JsonDeserializer(DebeziumDto.class));
        return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        BackOff fixedBackOff = new FixedBackOff(10, 1);
        return new DefaultErrorHandler((consumerRecord, exception) -> {}, fixedBackOff);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DebeziumDto> pironlineListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DebeziumDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pironlineConsumerFactory());
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }
}
