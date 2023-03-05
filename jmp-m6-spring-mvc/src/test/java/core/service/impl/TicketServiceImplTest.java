package core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.dao.impl.TicketDao;
import core.model.Ticket;
import core.model.impl.EventImpl;
import core.model.impl.TicketImpl;
import core.model.impl.UserImpl;
import core.service.TicketService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * TicketServiceImplTest
 * Date: 02/27/2023
 *
 * @author Yauheni Antsipenka
 */
@Ignore
public class TicketServiceImplTest {


    public static final Ticket FIRST_TICKET = new TicketImpl(1, 1, 1, Ticket.Category.PREMIUM, 1);
    public static final Ticket SECOND_TICKET = new TicketImpl(2, 2, 2, Ticket.Category.BAR, 2);
    public static final Map<Long, Ticket> MAP_TO_INSERT = Map.of(1L, FIRST_TICKET, 2L, SECOND_TICKET);
    private final TicketDao ticketDao = new TicketDao();
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        ticketDao.getTicketsMap().putAll(MAP_TO_INSERT);
        ticketService = new TicketServiceImpl(ticketDao);
    }

    @Test
    public void testBookTicket() {
        Ticket newTicket = ticketService.bookTicket(3, 3, 167, Ticket.Category.PREMIUM);
        assertEquals(newTicket, ticketDao.get(3).orElse(null));
    }

    @Test
    public void testGetBookedTicketsByUser() {
        List<Ticket> bookedTickets = ticketService.getBookedTickets(new UserImpl(1, "name", "email"), 0, 0);
        assertEquals(1, bookedTickets.size());
        assertEquals(FIRST_TICKET, bookedTickets.get(0));
    }

    @Test
    public void testGetBookedTicketsByUserWhenNotFound() {
        List<Ticket> bookedTickets = ticketService.getBookedTickets(new UserImpl(777, "name777", "email777"), 0, 0);
        assertEquals(0, bookedTickets.size());
    }

    @Test
    public void testGetBookedTicketsByEvent() {
        List<Ticket> bookedTickets =
            ticketService.getBookedTickets(new EventImpl(2, "name", LocalDate.of(2000, 1, 1)), 0, 0);
        assertEquals(1, bookedTickets.size());
        assertEquals(SECOND_TICKET, bookedTickets.get(0));
    }

    @Test
    public void testGetBookedTicketsByEventWhenNotFound() {
        List<Ticket> bookedTickets =
            ticketService.getBookedTickets(new EventImpl(777, "name777", LocalDate.of(2000, 1, 1)), 0, 0);
        assertEquals(0, bookedTickets.size());
    }
}
