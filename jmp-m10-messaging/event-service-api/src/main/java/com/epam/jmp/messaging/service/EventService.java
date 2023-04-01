package com.epam.jmp.messaging.service;

import com.epam.jmp.messaging.dto.Event;

import java.util.List;

/**
 * EventService
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventService {

    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    Event getEvent(Long id);
    void deleteEvent(Long id);
    List<Event> getAllEvents();
}
