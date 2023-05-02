package com.epam.jmp.kafka.client.service;

import com.epam.jmp.kafka.client.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * KafkaConsumer
 * Date: 03/25/2023
 *
 * @author Yauheni Antsipenka
 */
@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private OrderService orderService;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${notification.topic.name}",
        partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")),
        groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void listener1(Order order, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        listen(order, partition);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${notification.topic.name}",
        partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "0")),
        groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void listener2(Order order, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        listen(order, partition);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${notification.topic.name}",
        partitionOffsets = @PartitionOffset(partition = "2", initialOffset = "0")),
        groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void listener3(Order order, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        listen(order, partition);
    }

    private void listen(Order order, int partition) {
        LOGGER.info("Got a new order: {} from partition: {}", order, partition);
        Optional<Order> currentOrder = orderService.findById(order.getId());
        if (currentOrder.isPresent() && currentOrder.get().getStatus() != order.getStatus()) {
            orderService.save(order);
        }
    }
}
