package com.epam.jmp.messaging.service.rabbitmq;

import com.epam.jmp.messaging.dto.Event;
import com.epam.jmp.messaging.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * EventConsumer
 * Date: 04/02/2023
 *
 * @author Yauheni Antsipenka
 */
@Component
@Profile("rabbit")
public class EventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private EventRepository eventRepository;

    @RabbitListener(queues = "${create.request.topic.name}", containerFactory = "rabbitListenerContainerFactory")
    public void createEventListener(Event event) {
        LOGGER.info("Create new event: {}", event);
        eventRepository.save(event);
    }

    @RabbitListener(queues = "${update.request.topic.name}", containerFactory = "rabbitListenerContainerFactory")
    public void updateEventListener(Event event) {
        LOGGER.info("Update event: {}", event);
        Optional<Event> currentEvent = eventRepository.findById(event.getEventId());
        if (currentEvent.isPresent()) {
            eventRepository.save(event);
        }
    }

    @RabbitListener(queues = "${delete.request.topic.name}")
    public void deleteEventListener(String id) {
        LOGGER.info("Delete event with id: {}", id);
        eventRepository.deleteById(Long.parseLong(id));
    }
}
