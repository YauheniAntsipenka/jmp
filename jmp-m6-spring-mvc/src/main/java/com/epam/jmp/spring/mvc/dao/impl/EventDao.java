package com.epam.jmp.spring.mvc.dao.impl;

import com.epam.jmp.spring.mvc.dao.storage.Storage;
import com.epam.jmp.spring.mvc.dao.Dao;
import com.epam.jmp.spring.mvc.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * EventDao
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class EventDao implements Dao<Event> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDao.class);

    private Map<Long, Event> eventsMap = new HashMap<>();
    private Storage<Event> storage;

    @Override
    public Optional<Event> get(long id) {
        return Optional.ofNullable(eventsMap.get(id));
    }

    @Override
    public Collection<Event> getAll() {
        return eventsMap.values();
    }

    @Override
    public Event save(Event event) {
        if (eventsMap.keySet().stream().anyMatch(id -> id == event.getId())) {
            LOGGER.info("Event with id {} exists in map", event.getId());
            return null;
        }
        eventsMap.put(event.getId(), event);
        LOGGER.info("Event with id {} was created", event.getId());
        return event;
    }

    @Override
    public Event update(Event event) {
        if (eventsMap.keySet().stream().noneMatch(id -> id == event.getId())) {
            LOGGER.info("Event with id {} not exists in map", event.getId());
            return null;
        }
        Event eventToUpdate = eventsMap.get(event.getId());
        eventToUpdate.setDate(event.getDate());
        eventToUpdate.setTitle(event.getTitle());
        eventsMap.put(event.getId(), eventToUpdate);
        LOGGER.info("Event with id {} was updated", event.getId());
        return event;
    }

    @Override
    public boolean delete(long eventId) {
        if (eventsMap.keySet().stream().noneMatch(id -> id == eventId)) {
            LOGGER.info("Event with id {} not exists in map", eventId);
            return false;
        }
        eventsMap.remove(eventId);
        LOGGER.info("Event with id {} was removed", eventId);
        return true;
    }

    public Map<Long, Event> getEventsMap() {
        return eventsMap;
    }

    public void setStorage(Storage<Event> storage) {
        this.storage = storage;
    }

    private void init() {
        eventsMap.putAll(storage.retrieveInitDataFromFile());
    }
}
