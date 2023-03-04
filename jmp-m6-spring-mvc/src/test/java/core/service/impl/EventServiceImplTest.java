package core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.dao.impl.EventDao;
import core.model.Event;
import core.model.impl.EventImpl;
import core.service.EventService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * EventServiceImplTest
 * Date: 02/27/2023
 *
 * @author Yauheni Antsipenka
 */
@Ignore
public class EventServiceImplTest {

    public static final Event FIRST_EVENT = new EventImpl(1, "title1", Date.valueOf("1999-01-01"));
    public static final Event SECOND_EVENT = new EventImpl(2, "title2", Date.valueOf("1998-02-02"));
    public static final Map<Long, Event> MAP_TO_INSERT = Map.of(1L, FIRST_EVENT, 2L, SECOND_EVENT);
    public static final int NOT_FOUND_EVENT_ID = 789;
    public static final String NOT_FOUND_TITLE = "title789";
    private final EventDao eventDao = new EventDao();
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        eventDao.getEventsMap().putAll(MAP_TO_INSERT);
        eventService = new EventServiceImpl(eventDao);
    }

    @Test
    public void testGetEventById() {
        assertEquals(FIRST_EVENT, eventService.getEventById(1));
    }

    @Test
    public void testGetEventByIdWhenNotFound() {
        assertNull(eventService.getEventById(NOT_FOUND_EVENT_ID));
    }

    @Test
    public void testGetEventsByTitle() {
        List<Event> events = eventService.getEventsByTitle("title1", 0, 0);
        assertEquals(1, events.size());
        assertEquals(FIRST_EVENT, events.get(0));
    }

    @Test
    public void testGetEventsByTitleWhenNotFound() {
        List<Event> events = eventService.getEventsByTitle(NOT_FOUND_TITLE, 0, 0);
        assertEquals(0, events.size());
    }

    @Test
    public void testGetEventsForDay() {
        List<Event> events = eventService.getEventsForDay(Date.valueOf("1998-02-02"), 0, 0);
        assertEquals(1, events.size());
        assertEquals(SECOND_EVENT, eventService.getEventById(2));
    }

    @Test
    public void testGetEventsForDayWhenNotFound() {
        List<Event> events = eventService.getEventsForDay(Date.valueOf("1999-02-02"), 0, 0);
        assertEquals(0, events.size());
    }

    @Test
    public void testUpdate() {
        String newTitle = "title777";
        Event event = new EventImpl(1, newTitle, Date.valueOf("1999-01-01"));
        eventService.updateEvent(event);
        assertEquals(newTitle, Objects.requireNonNull(eventDao.get(1).orElse(null)).getTitle());
    }

    @Test
    public void testUpdateWhenNotFound() {
        Event event = new EventImpl(123, "megatitle", Date.valueOf("1999-01-01"));
        assertNull(eventService.updateEvent(event));
    }

    @Test
    public void testDelete() {
        assertTrue(eventDao.delete(1));
        assertNull(eventDao.get(1).orElse(null));
    }

    @Test
    public void testDeleteWhenNotFound() {
        assertFalse(eventDao.delete(NOT_FOUND_EVENT_ID));
    }

    @Test
    public void testCreate() {
        Event newEvent = new EventImpl(3, "title3", Date.valueOf("1993-03-03"));
        Event savedEvent = eventDao.save(newEvent);
        assertEquals(savedEvent, eventDao.get(3).orElse(null));
    }

    @Test
    public void testCreateWhenAlreadyExists() {
        Event newEvent = new EventImpl(1, "title1", Date.valueOf("1999-01-01"));
        assertNull(eventDao.save(newEvent));
    }
}
