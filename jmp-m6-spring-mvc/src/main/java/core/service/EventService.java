package core.service;

import core.model.Event;

import java.time.LocalDate;
import java.util.List;

/**
 * EventService
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventService {

    Event getEventById(long eventId);
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
    List<Event> getEventsForDay(LocalDate day, int pageSize, int pageNum);
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
