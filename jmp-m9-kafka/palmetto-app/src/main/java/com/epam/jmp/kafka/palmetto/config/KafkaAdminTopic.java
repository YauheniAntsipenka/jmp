package com.epam.jmp.kafka.palmetto.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaAdminTopic
 * Date: 03/24/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
public class KafkaAdminTopic {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value(value = "${order.topic.name}")
    private String orderTopicName;
    @Value(value = "${notification.topic.name}")
    private String notificationTopicName;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name(orderTopicName)
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder.name(notificationTopicName)
            .partitions(3)
            .replicas(1)
            .build();
    }
}
