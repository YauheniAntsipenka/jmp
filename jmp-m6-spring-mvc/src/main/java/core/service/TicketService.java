package core.service;

import core.model.Event;
import core.model.Ticket;
import core.model.User;

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
