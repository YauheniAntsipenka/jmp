package com.epam.jmp.messaging.service.impl;

import com.epam.jmp.messaging.dto.Event;
import com.epam.jmp.messaging.repository.EventRepository;
import com.epam.jmp.messaging.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Optional<Event> currentEvent = eventRepository.findById(id);
        if (currentEvent.isPresent()) {
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public Event findEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> findAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }
}
