package com.epam.jmp.messaging.repository;

import com.epam.jmp.messaging.dto.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * EventRepository
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
