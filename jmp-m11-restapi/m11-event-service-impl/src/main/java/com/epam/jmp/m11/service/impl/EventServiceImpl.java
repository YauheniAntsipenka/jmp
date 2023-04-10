package com.epam.jmp.m11.service.impl;

import com.epam.jmp.m11.dto.Event;
import com.epam.jmp.m11.service.api.repository.EventRepository;
import com.epam.jmp.m11.service.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        return eventRepository.findAll().stream()
            .filter(event -> event.getTitle().equals(title))
            .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public Event findEvent(Long id) {
        return null;
    }

    @Override
    public void deleteEvent(Long id) {

    }

    @Override
    public List<Event> findAllEvents() {
        return null;
    }
}
