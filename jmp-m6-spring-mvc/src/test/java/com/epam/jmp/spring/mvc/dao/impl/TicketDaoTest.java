package com.epam.jmp.spring.mvc.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.epam.jmp.spring.mvc.model.impl.TicketImpl;
import com.epam.jmp.spring.mvc.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

/**
 * TicketDaoTest
 * Date: 02/27/2023
 *
 * @author Yauheni Antsipenka
 */
public class TicketDaoTest {

    public static final Ticket FIRST_TICKET = new TicketImpl(1, 1, 1, Ticket.Category.PREMIUM, 1);
    public static final Ticket SECOND_TICKET = new TicketImpl(2, 2, 2, Ticket.Category.BAR, 2);
    public static final Map<Long, Ticket> MAP_TO_INSERT = Map.of(1L, FIRST_TICKET, 2L, SECOND_TICKET);
    private TicketDao ticketDao;

    @BeforeEach
    public void setUp() {
        ticketDao = new TicketDao();
        ticketDao.getTicketsMap().putAll(MAP_TO_INSERT);
    }

    @Test
    public void testGetById() {
        Ticket ticket = ticketDao.get(2).orElse(null);
        assertNotNull(ticket);
        assertEquals(SECOND_TICKET, ticket);
    }

    @Test
    public void testGetAll() {
        assertEquals(2, ticketDao.getAll().size());
        assertEquals(ticketDao.get(1).orElse(null), MAP_TO_INSERT.get(1L));
        assertEquals(ticketDao.get(2).orElse(null), MAP_TO_INSERT.get(2L));
    }

    @Test
    public void testUpdate() {
        Ticket ticket = new TicketImpl(1, 2, 1, Ticket.Category.PREMIUM, 1);
        ticketDao.update(ticket);
        assertEquals(2, Objects.requireNonNull(ticketDao.get(1).orElse(null)).getEventId());
    }

    @Test
    public void testDelete() {
        ticketDao.delete(1);
        assertNull(ticketDao.get(1).orElse(null));
    }

    @Test
    public void testSave() {
        Ticket newTicket = new TicketImpl(3, 3, 3, Ticket.Category.STANDARD, 3);
        ticketDao.save(newTicket);
        assertEquals(newTicket, ticketDao.get(3).orElse(null));
    }
}
