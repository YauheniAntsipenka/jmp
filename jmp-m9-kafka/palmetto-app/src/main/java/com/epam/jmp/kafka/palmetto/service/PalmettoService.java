package com.epam.jmp.kafka.palmetto.service;

import com.epam.jmp.kafka.palmetto.domain.Order;
import com.epam.jmp.kafka.palmetto.domain.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * PalmettoService
 * Date: 03/25/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class PalmettoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PalmettoService.class);

    @Value(value = "${notification.topic.name}")
    private String notificationTopicName;

    @Autowired
    private KafkaService kafkaService;

    public void cook(Order order) throws InterruptedException {
        Thread.sleep(2000);
        order.setStatus(StatusEnum.READY_FOR_DELIVERY);
        kafkaService.sendMessage(notificationTopicName, String.valueOf(order));
        LOGGER.info("Order {} is ready for delivery", order);
    }
}
