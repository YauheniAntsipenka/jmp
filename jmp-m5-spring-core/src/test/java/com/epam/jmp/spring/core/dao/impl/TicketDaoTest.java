package com.epam.jmp.spring.core.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.epam.jmp.spring.core.dao.TicketDao;
import com.epam.jmp.spring.core.model.Category;
import com.epam.jmp.spring.core.model.Ticket;
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

    public static final Ticket FIRST_TICKET = new Ticket(1, 1, 1, Category.PREMIUM, 1);
    public static final Ticket SECOND_TICKET = new Ticket(2, 2, 2, Category.BAR, 2);
    public static final Map<Long, Ticket> MAP_TO_INSERT = Map.of(1L, FIRST_TICKET, 2L, SECOND_TICKET);
    private TicketDao ticketDao;

    @BeforeEach
    public void setUp() {
        ticketDao = new TicketDaoImpl();
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
        Ticket ticket = new Ticket(1, 2, 1, Category.PREMIUM, 1);
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
        Ticket newTicket = new Ticket(3, 3, 3, Category.STANDARD, 3);
        ticketDao.save(newTicket);
        assertEquals(newTicket, ticketDao.get(3).orElse(null));
    }
}
