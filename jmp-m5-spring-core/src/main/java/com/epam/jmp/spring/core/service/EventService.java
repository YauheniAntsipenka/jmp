package com.epam.jmp.spring.core.service;

import com.epam.jmp.spring.core.model.Event;

import java.time.LocalDate;
import java.util.List;

/**
 * EventService
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventService {

    Event findEventById(long eventId);
    List<Event> findEventsByTitle(String title, int pageSize, int pageNum);
    List<Event> findEventsForDay(LocalDate day, int pageSize, int pageNum);
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
