package com.epam.jmp.messaging.service.impl;

import com.epam.jmp.messaging.EventMessaging;
import com.epam.jmp.messaging.dto.Event;
import com.epam.jmp.messaging.repository.EventRepository;
import com.epam.jmp.messaging.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EventServiceImpl
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired(required = false)
    private EventMessaging eventMessaging;

    @Override
    public Event createEvent(Event event) {
        eventMessaging.createEvent(event);
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        eventMessaging.updateEvent(event);
        return event;
    }

    @Override
    public void deleteEvent(Long id) {
        eventMessaging.deleteEvent(id);
    }

    @Override
    public Event findEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> findAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }
}
