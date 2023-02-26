package com.epam.jmp.spring.core.service;

import com.epam.jmp.spring.core.model.Event;

import java.util.Date;
import java.util.List;

/**
 * EventService
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventService {

    Event getEventById(long eventId);
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
