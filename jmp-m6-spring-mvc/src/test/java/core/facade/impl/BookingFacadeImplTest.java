package core.facade.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.facade.BookingFacade;
import core.model.Event;
import core.model.Ticket;
import core.model.User;
import core.model.impl.EventImpl;
import core.model.impl.UserImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

/**
 * BookingFacadeImplTest
 * Date: 02/27/2023
 *
 * @author Yauheni Antsipenka
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/context/ApplicationContext.xml" })
public class BookingFacadeImplTest {

    @Autowired
    private BookingFacade bookingFacade;

    @Test
    void testBookTickets() {
        User user = new UserImpl(1, "name1", "email1");
        Event event = new EventImpl(1, "title1", LocalDate.of(1999, 1, 1));
        bookingFacade.createUser(user);
        bookingFacade.createEvent(event);
        bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.PREMIUM);
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(user, 1, 1);
        assertEquals(2, bookedTickets.size());
    }

    @Test
    void testCancelBookedTickets() {
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(new UserImpl(1, "name1", "email1"), 1, 1);
        bookedTickets.forEach(ticket -> bookingFacade.cancelTicket(ticket.getId()));
        List<Ticket> bookedTicketsAfterCancelling =
            bookingFacade.getBookedTickets(new UserImpl(1, "name1", "email1"), 1, 1);
        assertEquals(0, bookedTicketsAfterCancelling.size());
    }

}
