package com.epam.jmp.m11.service.api.repository;

import com.epam.jmp.m11.dto.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EventRepository
 * Date: 04/08/2023
 *
 * @author Yauheni Antsipenka
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.title = ?1")
    List<Event> findAllEventsByTitle(String title);
}
