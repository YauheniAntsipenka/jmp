package com.epam.jmp.kafka.courier.service;

import com.epam.jmp.kafka.courier.domain.Order;
import com.epam.jmp.kafka.courier.domain.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

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
    private CourierService courierService;

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
        try {
            LOGGER.info("Got a new order: {} from partition: {}", order, partition);
            if (order.getStatus() == StatusEnum.READY_FOR_DELIVERY) {
                courierService.deliver(order);
            }
        } catch (InterruptedException e) {
            LOGGER.error("Exception occurred.", e);
        }
    }
}
