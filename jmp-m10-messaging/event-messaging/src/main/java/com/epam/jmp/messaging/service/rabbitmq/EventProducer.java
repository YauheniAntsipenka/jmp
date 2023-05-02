package com.epam.jmp.messaging.service.rabbitmq;

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
@Profile("rabbit")
public class EventProducer implements EventMessaging {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

    @Value(value = "${create.notification.routing.key}")
    private String createNotificationRoutingKey;
    @Value(value = "${update.notification.routing.key}")
    private String updateNotificationRoutingKey;
    @Value(value = "${delete.notification.routing.key}")
    private String deleteNotificationRoutingKey;
    @Value(value = "${create.request.routing.key}")
    private String createRequestRoutingKey;
    @Value(value = "${update.request.routing.key}")
    private String updateRequestRoutingKey;
    @Value(value = "${delete.request.routing.key}")
    private String deleteRequestRoutingKey;

    @Autowired
    private RabbitMQService rabbitMQService;

    @Override
    public void createEvent(Event event) {
        rabbitMQService.sendMessage(createNotificationRoutingKey, String.valueOf(event));
        rabbitMQService.sendMessage(createRequestRoutingKey, String.valueOf(event));
        LOGGER.info("Message to create event {} was sent", event);
    }

    @Override
    public void updateEvent(Event event) {
        rabbitMQService.sendMessage(updateNotificationRoutingKey, String.valueOf(event));
        rabbitMQService.sendMessage(updateRequestRoutingKey, String.valueOf(event));
        LOGGER.info("Message to update event {} was sent", event);
    }

    @Override
    public void deleteEvent(Long id) {
        rabbitMQService.sendMessage(deleteNotificationRoutingKey, String.valueOf(id));
        rabbitMQService.sendMessage(deleteRequestRoutingKey, String.valueOf(id));
        LOGGER.info("Message to delete event with id {} was sent", id);
    }
}
