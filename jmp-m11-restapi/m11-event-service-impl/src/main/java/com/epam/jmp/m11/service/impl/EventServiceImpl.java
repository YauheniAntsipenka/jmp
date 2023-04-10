package com.epam.jmp.m11.service.impl;

import com.epam.jmp.m11.dto.Event;
import com.epam.jmp.m11.service.api.repository.EventRepository;
import com.epam.jmp.m11.service.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EventServiceImpl
 * Date: 04/08/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAllEventsByTitle(String title) {
        return eventRepository.findAllEventsByTitle(title);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        if (!eventRepository.existsById(event.getEventId())) {
            return null;
        }
        return eventRepository.save(event);
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
