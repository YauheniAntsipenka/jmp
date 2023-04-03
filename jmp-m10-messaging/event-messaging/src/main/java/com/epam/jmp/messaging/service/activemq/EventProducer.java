package com.epam.jmp.messaging.service.activemq;

import com.epam.jmp.messaging.EventMessaging;
import com.epam.jmp.messaging.dto.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * EventProducer
 * Date: 04/03/2023
 *
 * @author Yauheni Antsipenka
 */
@Component
@Profile("activemq")
public class EventProducer implements EventMessaging {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

    @Value(value = "${create.notification.queue.name}")
    private String createNotificationQueueName;
    @Value(value = "${update.notification.queue.name}")
    private String updateNotificationQueueName;
    @Value(value = "${delete.notification.queue.name}")
    private String deleteNotificationQueueName;
    @Value(value = "${create.request.queue.name}")
    private String createRequestQueueName;
    @Value(value = "${update.request.queue.name}")
    private String updateRequestQueueName;
    @Value(value = "${delete.request.queue.name}")
    private String deleteRequestQueueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void createEvent(Event event) {
        jmsTemplate.convertAndSend(createNotificationQueueName, event);
        jmsTemplate.convertAndSend(createRequestQueueName, event);
        LOGGER.info("Message to create event {} was sent", event);
    }

    @Override
    public void updateEvent(Event event) {
        jmsTemplate.convertAndSend(updateNotificationQueueName, event);
        jmsTemplate.convertAndSend(updateRequestQueueName, event);
        LOGGER.info("Message to update event {} was sent", event);
    }

    @Override
    public void deleteEvent(Long id) {
        jmsTemplate.convertAndSend(deleteNotificationQueueName, id);
        jmsTemplate.convertAndSend(deleteRequestQueueName, id);
        LOGGER.info("Message to delete event with id {} was sent", id);
    }
}
