package com.epam.jmp.messaging.service.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaAdminConfig
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
@Profile("kafka")
public class KafkaAdminConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value(value = "${create.notification.topic.name}")
    private String createNotificationTopicName;
    @Value(value = "${update.notification.topic.name}")
    private String updateNotificationTopicName;
    @Value(value = "${delete.notification.topic.name}")
    private String deleteNotificationTopicName;
    @Value(value = "${create.request.topic.name}")
    private String createRequestTopicName;
    @Value(value = "${update.request.topic.name}")
    private String updateRequestTopicName;
    @Value(value = "${delete.request.topic.name}")
    private String deleteRequestTopicName;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic createNotificationTopic() {
        return TopicBuilder.name(createNotificationTopicName)
            .partitions(1)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic updateNotificationTopic() {
        return TopicBuilder.name(updateNotificationTopicName)
            .partitions(1)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic deleteNotificationTopic() {
        return TopicBuilder.name(deleteNotificationTopicName)
            .partitions(1)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic createRequestTopic() {
        return TopicBuilder.name(createRequestTopicName)
            .partitions(1)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic updateRequestTopic() {
        return TopicBuilder.name(updateRequestTopicName)
            .partitions(1)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic deleteRequestTopic() {
        return TopicBuilder.name(deleteRequestTopicName)
            .partitions(1)
            .replicas(1)
            .build();
    }
}
