package com.epam.jmp.kafka.client.repository;

import com.epam.jmp.kafka.client.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderRepository
 * Date: 03/25/2023
 *
 * @author Yauheni Antsipenka
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
