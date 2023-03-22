package com.epam.jmp.spring.core.facade.impl;

import com.epam.jmp.spring.core.facade.BookingFacade;
import com.epam.jmp.spring.core.model.Category;
import com.epam.jmp.spring.core.model.Event;
import com.epam.jmp.spring.core.model.Ticket;
import com.epam.jmp.spring.core.model.User;
import com.epam.jmp.spring.core.service.EventService;
import com.epam.jmp.spring.core.service.TicketService;
import com.epam.jmp.spring.core.service.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * BookingFacadeImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class BookingFacadeImpl implements BookingFacade {

    private final EventService eventService;
    private final TicketService ticketService;
    private final UserService userService;

    public BookingFacadeImpl(EventService eventService, TicketService ticketService, UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.findEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.findEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(LocalDate day, int pageSize, int pageNum) {
        return eventService.findEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.findUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    @Override
    public List<User> findUsersByName(String name, int pageSize, int pageNum) {
        return userService.findUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.findBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.findBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
