package com.epam.jmp.kafka.courier.service;

import com.epam.jmp.kafka.courier.domain.Order;
import com.epam.jmp.kafka.courier.domain.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * CourierService
 * Date: 03/25/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class CourierService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourierService.class);

    @Value(value = "${notification.topic.name}")
    private String notificationTopicName;

    @Autowired
    private KafkaService kafkaService;

    public void deliver(Order order) throws InterruptedException {
        Thread.sleep(3000);
        order.setStatus(StatusEnum.DELIVERED);
        kafkaService.sendMessage(notificationTopicName, String.valueOf(order));
        LOGGER.info("Order {} is delivered", order);
    }
}
