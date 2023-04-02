package com.epam.jmp.messaging.service.kafka;

import com.epam.jmp.messaging.dto.Event;
import com.epam.jmp.messaging.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * EventConsumer
 * Date: 04/02/2023
 *
 * @author Yauheni Antsipenka
 */
@Component
@Profile("kafka")
public class EventConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private EventRepository eventRepository;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${create.request.topic.name}",
        partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")),
        groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void createEventListener(Event event) {
        LOGGER.info("Create new event: {}", event);
        eventRepository.save(event);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${update.request.topic.name}",
        partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")),
        groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void updateEventListener(Event event) {
        LOGGER.info("Update event: {}", event);
        Optional<Event> currentEvent = eventRepository.findById(event.getEventId());
        if (currentEvent.isPresent()) {
            eventRepository.save(event);
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${delete.request.topic.name}",
        partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")),
        groupId = "${spring.kafka.groupId}", containerFactory = "kafkaPathParamListenerContainerFactory")
    public void createEventListener(String id) {
        LOGGER.info("Delete event with id: {}", id);
        eventRepository.deleteById(Long.parseLong(id));
    }
}

