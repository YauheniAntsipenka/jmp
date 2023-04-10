package com.epam.jmp.m11.service.api.service;

import com.epam.jmp.m11.dto.Event;

import java.util.List;

/**
 * EventService
 * Date: 04/08/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Event event);
    Event findEvent(Long id);
    void deleteEvent(Long id);
    List<Event> findAllEvents();
    List<Event> findAllEventsByTitle(String title);
}
