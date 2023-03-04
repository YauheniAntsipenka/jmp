package core.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.model.Event;
import core.model.impl.EventImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

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
        Collection<Event> events = eventDao.getAll();
        assertEquals(2, events.size());
        assertTrue(events.contains(MAP_TO_INSERT.get(1L)));
        assertTrue(events.contains(MAP_TO_INSERT.get(2L)));
    }

    @Test
    public void testUpdate() {
        String newTitle = "title777";
        Event event = new EventImpl(1, newTitle, Date.valueOf("1999-01-01"));
        Event updatedEvent = eventDao.update(event);
        assertEquals(newTitle, updatedEvent.getTitle());
    }

    @Test
    public void testDelete() {
        assertTrue(eventDao.delete(1));
    }

    @Test
    public void testSave() {
        Event newEvent = new EventImpl(3, "title3", Date.valueOf("1993-03-03"));
        Event savedEvent = eventDao.save(newEvent);
        assertEquals(newEvent, savedEvent);
    }
}
