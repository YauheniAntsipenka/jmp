package com.epam.jmp.spring.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.jmp.spring.core.dao.impl.EventDaoImpl;
import com.epam.jmp.spring.core.exception.JMPDeleteException;
import com.epam.jmp.spring.core.exception.JMPSaveException;
import com.epam.jmp.spring.core.exception.JMPUpdateException;
import com.epam.jmp.spring.core.model.Event;
import com.epam.jmp.spring.core.service.EventService;
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

    public static final Event FIRST_EVENT = new Event(1, "title1", LocalDate.of(1999, 1, 1));
    public static final Event SECOND_EVENT = new Event(2, "title2", LocalDate.of(1998, 2, 2));
    public static final Map<Long, Event> MAP_TO_INSERT = Map.of(1L, FIRST_EVENT, 2L, SECOND_EVENT);
    public static final int NOT_FOUND_EVENT_ID = 789;
    public static final String NOT_FOUND_TITLE = "title789";
    private final EventDaoImpl eventDao = new EventDaoImpl();
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        eventDao.getEventsMap().putAll(MAP_TO_INSERT);
        eventService = new EventServiceImpl(eventDao);
    }

    @Test
    public void testGetEventById() {
        assertEquals(FIRST_EVENT, eventService.findEventById(1));
    }

    @Test
    public void testGetEventByIdWhenNotFound() {
        assertNull(eventService.findEventById(NOT_FOUND_EVENT_ID));
    }

    @Test
    public void testGetEventsByTitle() {
        List<Event> events = eventService.findEventsByTitle("title1", 0, 0);
        assertEquals(1, events.size());
        assertEquals(FIRST_EVENT, events.get(0));
    }

    @Test
    public void testGetEventsByTitleWhenNotFound() {
        List<Event> events = eventService.findEventsByTitle(NOT_FOUND_TITLE, 0, 0);
        assertEquals(0, events.size());
    }

    @Test
    public void testGetEventsForDay() {
        List<Event> events = eventService.findEventsForDay(LocalDate.of(1998, 2, 2), 0, 0);
        assertEquals(1, events.size());
        assertEquals(SECOND_EVENT, eventService.findEventById(2));
    }

    @Test
    public void testGetEventsForDayWhenNotFound() {
        List<Event> events = eventService.findEventsForDay(LocalDate.of(1999, 2, 2), 0, 0);
        assertEquals(0, events.size());
    }

    @Test
    public void testUpdate() {
        String newTitle = "title777";
        Event event = new Event(1, newTitle, LocalDate.of(1999, 1, 1));
        eventService.updateEvent(event);
        assertEquals(newTitle, Objects.requireNonNull(eventDao.get(1).orElse(null)).getTitle());
    }

    @Test
    public void testUpdateWhenNotFound() {
        Event event = new Event(123, "megatitle", LocalDate.of(1999, 1, 1));
        assertThrows(JMPUpdateException.class, () -> eventService.updateEvent(event));
    }

    @Test
    public void testDelete() {
        assertTrue(eventDao.delete(1));
        assertNull(eventDao.get(1).orElse(null));
    }

    @Test
    public void testDeleteWhenNotFound() {
        assertThrows(JMPDeleteException.class, () -> eventDao.delete(NOT_FOUND_EVENT_ID));
    }

    @Test
    public void testCreate() {
        Event newEvent = new Event(3, "title3", LocalDate.of(1993, 3, 3));
        Event savedEvent = eventDao.save(newEvent);
        assertEquals(savedEvent, eventDao.get(3).orElse(null));
    }

    @Test
    public void testCreateWhenAlreadyExists() {
        Event newEvent = new Event(1, "title1", LocalDate.of(1999, 1, 1));
        assertThrows(JMPSaveException.class, () -> eventDao.save(newEvent));
    }
}
