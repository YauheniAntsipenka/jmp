package com.epam.jmp.messaging.service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final String MESSAGE_CONTENT_TYPE = "application/json";

    @Value(value = "${exchange.key}")
    private String exchangeKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String routingKey, String messageToSend) {
        rabbitTemplate.convertAndSend(exchangeKey, routingKey, messageToSend, message -> {
            message.getMessageProperties().setContentType(MESSAGE_CONTENT_TYPE);
            return message;
        });
    }

}
