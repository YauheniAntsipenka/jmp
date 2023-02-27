package com.epam.jmp.spring.core.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.epam.jmp.spring.core.model.Event;
import com.epam.jmp.spring.core.model.impl.EventImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;

/**
 * EventDaoTest
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class EventDaoTest {

    public static final Event FIRST_EVENT = new EventImpl(1, "title1", Date.valueOf("1999-01-01"));
    public static final Event SECOND_EVENT = new EventImpl(2, "title2", Date.valueOf("1998-02-02"));
    public static final Map<Long, Event> MAP_TO_INSERT = Map.of(1L, FIRST_EVENT, 2L, SECOND_EVENT);
    private EventDao eventDao;

    @BeforeEach
    public void setUp() {
        eventDao = new EventDao();
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
        Event event = new EventImpl(1, newTitle, Date.valueOf("1999-01-01"));
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
        Event newEvent = new EventImpl(3, "title3", Date.valueOf("1993-03-03"));
        eventDao.save(newEvent);
        assertEquals(newEvent, eventDao.get(3).orElse(null));
    }
}
