package com.epam.jmp.messaging.service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * RabbitMQService
 * Date: 04/02/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
@Profile("rabbit")
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String routingKey, String messageToSend) {
        rabbitTemplate.convertAndSend("testExchange", routingKey, messageToSend, message -> {
            message.getMessageProperties().setContentType("application/json");
            return message;
        });
    }

}
