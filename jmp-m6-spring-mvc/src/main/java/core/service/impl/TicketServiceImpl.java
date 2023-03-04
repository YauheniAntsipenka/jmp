package core.service.impl;

import core.dao.impl.TicketDao;
import core.model.Event;
import core.model.Ticket;
import core.model.User;
import core.model.impl.TicketImpl;
import core.service.TicketService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TicketServiceImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticketWithLastId = ticketDao.getAll().stream().max(Comparator.comparingLong(Ticket::getId)).orElse(null);
        if (ticketWithLastId != null) {
            return ticketDao.save(new TicketImpl(ticketWithLastId.getId() + 1, userId, eventId, category, place));
        }
        return ticketDao.save(new TicketImpl(1, userId, eventId, category, place));
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.getAll().stream()
            .filter(ticket -> ticket.getUserId() == user.getId())
            .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.getAll().stream()
            .filter(ticket -> ticket.getEventId() == event.getId())
            .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDao.delete(ticketId);
    }
}
