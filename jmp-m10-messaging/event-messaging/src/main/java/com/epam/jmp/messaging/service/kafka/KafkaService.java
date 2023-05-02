package com.epam.jmp.messaging.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * KafkaService
 * Date: 04/02/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class KafkaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message).completable();
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Sent message {} to topic {}", message, topicName);
            } else {
                LOGGER.error("Unable to send message {}. Exception: {}", message, ex.getMessage());
            }
        });
    }
}
