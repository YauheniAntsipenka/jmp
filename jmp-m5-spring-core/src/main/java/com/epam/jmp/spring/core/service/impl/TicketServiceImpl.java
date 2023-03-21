package com.epam.jmp.spring.core.service.impl;

import com.epam.jmp.spring.core.dao.impl.TicketDaoImpl;
import com.epam.jmp.spring.core.model.Category;
import com.epam.jmp.spring.core.model.Event;
import com.epam.jmp.spring.core.model.Ticket;
import com.epam.jmp.spring.core.model.User;
import com.epam.jmp.spring.core.service.TicketService;

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

    private TicketDaoImpl ticketDao;

    public void setTicketDao(TicketDaoImpl ticketDao) {
        this.ticketDao = ticketDao;
    }

    public TicketServiceImpl(TicketDaoImpl ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        Ticket ticketWithLastId = ticketDao.getAll().stream().max(Comparator.comparingLong(Ticket::getId)).orElse(null);

        if (ticketWithLastId != null) {
            return ticketDao.save(new Ticket(ticketWithLastId.getId() + 1, userId, eventId, category, place));
        }

        return ticketDao.save(new Ticket(1, userId, eventId, category, place));
    }

    @Override
    public List<Ticket> findBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.getAll().stream()
            .filter(ticket -> ticket.getUserId() == user.getId())
            .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.getAll().stream()
            .filter(ticket -> ticket.getEventId() == event.getId())
            .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDao.delete(ticketId);
    }
}
