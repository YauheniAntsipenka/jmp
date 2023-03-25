package com.epam.jmp.kafka.client.controller;

import com.epam.jmp.kafka.client.domain.Order;
import com.epam.jmp.kafka.client.domain.StatusEnum;
import com.epam.jmp.kafka.client.service.KafkaService;
import com.epam.jmp.kafka.client.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * OrderController
 * Date: 03/24/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private KafkaService kafkaService;
    @Autowired
    private OrderService orderService;
    @Value(value = "${order.topic.name}")
    private String orderTopicName;
    @Value(value = "${notification.topic.name}")
    private String notificationTopicName;

    @PostMapping("/create")
    public void createOrder(@RequestBody Order order) {
        order.setStatus(StatusEnum.NEW);
        Order savedOrder = orderService.save(order);
        if (savedOrder == null) {
            LOGGER.error("Order wasn't created");
        } else {
            kafkaService.sendMessage(orderTopicName, String.valueOf(order));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderInfo(@PathVariable Integer id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(order.get());
    }
}
