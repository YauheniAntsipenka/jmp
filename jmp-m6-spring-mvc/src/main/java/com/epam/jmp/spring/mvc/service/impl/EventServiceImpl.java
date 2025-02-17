package com.epam.jmp.spring.mvc.service.impl;

import com.epam.jmp.spring.mvc.dao.impl.EventDao;
import com.epam.jmp.spring.mvc.model.Event;
import com.epam.jmp.spring.mvc.service.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EventServiceImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventDao.get(eventId).orElse(null);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDao.getAll().stream()
            .filter(event -> event.getTitle().equals(title))
            .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(LocalDate day, int pageSize, int pageNum) {
        return eventDao.getAll().stream()
            .filter(event -> event.getDate().equals(day))
            .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDao.update(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventDao.delete(eventId);
    }
}
