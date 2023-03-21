package com.epam.jmp.spring.core.dao.storage.impl;

import com.epam.jmp.spring.core.dao.storage.Storage;
import com.epam.jmp.spring.core.exception.RetrieveDataException;
import com.epam.jmp.spring.core.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * EventsStorageImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class EventsStorageImpl implements Storage<Event> {

    private static final String PATH = "init/events.json";

    @Override
    public Map<Long, Event> retrieveInitDataFromFile() {
        Map<Long, Event> eventsMap = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Arrays.asList(objectMapper.readValue(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(PATH)).toURI()), Event[].class))
                .forEach(event -> eventsMap.put(event.getId(), event));
        } catch (IOException | URISyntaxException e) {
            throw new RetrieveDataException(e);
        }
        return eventsMap;
    }
}
