package com.epam.jmp.kafka.client.service;

import com.epam.jmp.kafka.client.domain.Order;
import com.epam.jmp.kafka.client.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * OrderService
 * Date: 03/25/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }
}
