package com.epam.jmp.spring.core.service;

import com.epam.jmp.spring.core.model.Event;
import com.epam.jmp.spring.core.model.Ticket;
import com.epam.jmp.spring.core.model.User;

import java.util.List;

/**
 * TicketService
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
    boolean cancelTicket(long ticketId);
}
