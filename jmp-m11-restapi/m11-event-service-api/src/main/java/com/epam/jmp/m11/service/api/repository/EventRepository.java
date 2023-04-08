package com.epam.jmp.m11.service.api.repository;

import com.epam.jmp.m11.dto.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * EventRepository
 * Date: 04/08/2023
 *
 * @author Yauheni Antsipenka
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
