package com.epam.jmp.spring.core.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.epam.jmp.spring.core.dao.EventDao;
import com.epam.jmp.spring.core.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

/**
 * EventDaoTest
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class EventDaoTest {

    public static final Event FIRST_EVENT = new Event(1, "title1", LocalDate.of(1999, 1, 1));
    public static final Event SECOND_EVENT = new Event(2, "title2", LocalDate.of(1998, 2, 2));
    public static final Map<Long, Event> MAP_TO_INSERT = Map.of(1L, FIRST_EVENT, 2L, SECOND_EVENT);
    private EventDao eventDao;

    @BeforeEach
    public void setUp() {
        eventDao = new EventDaoImpl();
        eventDao.getEventsMap().putAll(MAP_TO_INSERT);
    }

    @Test
    public void testGetById() {
        Event event = eventDao.get(2).orElse(null);
        assertNotNull(event);
        assertEquals(SECOND_EVENT, event);
    }

    @Test
    public void testGetAll() {
        assertEquals(2, eventDao.getAll().size());
        assertEquals(eventDao.get(1).orElse(null), MAP_TO_INSERT.get(1L));
        assertEquals(eventDao.get(2).orElse(null), MAP_TO_INSERT.get(2L));
    }

    @Test
    public void testUpdate() {
        String newTitle = "title777";
        Event event = new Event(1, newTitle, LocalDate.of(1999, 1, 1));
        eventDao.update(event);
        assertEquals(newTitle, Objects.requireNonNull(eventDao.get(1).orElse(null)).getTitle());
    }

    @Test
    public void testDelete() {
        eventDao.delete(1);
        assertNull(eventDao.get(1).orElse(null));
    }

    @Test
    public void testSave() {
        Event newEvent = new Event(3, "title3", LocalDate.of(1993, 3, 3));
        eventDao.save(newEvent);
        assertEquals(newEvent, eventDao.get(3).orElse(null));
    }
}
