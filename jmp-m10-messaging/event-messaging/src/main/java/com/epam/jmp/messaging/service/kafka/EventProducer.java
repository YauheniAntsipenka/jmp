package com.epam.jmp.messaging.service.kafka;

import com.epam.jmp.messaging.EventMessaging;
import com.epam.jmp.messaging.dto.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * EventProducer
 * Date: 04/02/2023
 *
 * @author Yauheni Antsipenka
 */
@Component
@Profile("kafka")
public class EventProducer implements EventMessaging {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

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
    @Autowired
    private KafkaService kafkaService;

    @Override
    public void createEvent(Event event) {
        kafkaService.sendMessage(createNotificationTopicName, String.valueOf(event));
        kafkaService.sendMessage(createRequestTopicName, String.valueOf(event));
        LOGGER.info("Message to create event {} was sent", event);
    }

    @Override
    public void updateEvent(Event event) {
        kafkaService.sendMessage(updateNotificationTopicName, String.valueOf(event));
        kafkaService.sendMessage(updateRequestTopicName, String.valueOf(event));
        LOGGER.info("Message to update event {} was sent", event);
    }

    @Override
    public void deleteEvent(Long id) {
        kafkaService.sendMessage(deleteNotificationTopicName, String.valueOf(id));
        kafkaService.sendMessage(deleteRequestTopicName, String.valueOf(id));
        LOGGER.info("Message to delete event with id {} was sent", id);
    }
}
