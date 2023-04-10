package com.epam.jmp.spring.mvc.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.jmp.spring.mvc.dao.impl.EventDao;
import com.epam.jmp.spring.mvc.model.impl.EventImpl;
import com.epam.jmp.spring.mvc.model.Event;
import com.epam.jmp.spring.mvc.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * EventServiceImplTest
 * Date: 02/27/2023
 *
 * @author Yauheni Antsipenka
 */
public class EventServiceImplTest {

    public static final Event FIRST_EVENT = new EventImpl(1, "title1", LocalDate.of(1999, 1, 1));
    public static final Event SECOND_EVENT = new EventImpl(2, "title2", LocalDate.of(1998, 2, 2));
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
        List<Event> events = eventService.getEventsForDay(LocalDate.of(1998, 2, 2), 0, 0);
        assertEquals(1, events.size());
        assertEquals(SECOND_EVENT, eventService.getEventById(2));
    }

    @Test
    public void testGetEventsForDayWhenNotFound() {
        List<Event> events = eventService.getEventsForDay(LocalDate.of(1999, 2, 2), 0, 0);
        assertEquals(0, events.size());
    }

    @Test
    public void testUpdate() {
        String newTitle = "title777";
        Event event = new EventImpl(1, newTitle, LocalDate.of(1999, 1, 1));
        eventService.updateEvent(event);
        assertEquals(newTitle, Objects.requireNonNull(eventDao.get(1).orElse(null)).getTitle());
    }

    @Test
    public void testUpdateWhenNotFound() {
        Event event = new EventImpl(123, "megatitle", LocalDate.of(1999, 1, 1));
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
        Event newEvent = new EventImpl(3, "title3", LocalDate.of(1993, 3, 3));
        Event savedEvent = eventDao.save(newEvent);
        assertEquals(savedEvent, eventDao.get(3).orElse(null));
    }

    @Test
    public void testCreateWhenAlreadyExists() {
        Event newEvent = new EventImpl(1, "title1", LocalDate.of(1999, 1, 1));
        assertNull(eventDao.save(newEvent));
    }
}
