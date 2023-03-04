package core.dao.storage.impl;

import core.dao.storage.Storage;
import core.model.Event;
import core.model.impl.EventImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            Arrays.asList(objectMapper.readValue(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(PATH)).toURI()), EventImpl[].class))
                .forEach(event -> eventsMap.put(event.getId(), event));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return eventsMap;
    }
}
