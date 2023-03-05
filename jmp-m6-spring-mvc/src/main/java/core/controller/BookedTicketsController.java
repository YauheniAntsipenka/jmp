package core.controller;

import core.facade.BookingFacade;
import core.model.Ticket;
import core.model.User;
import core.util.GeneratePdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * BookedTicketsController
 * Date: 03/05/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@RequestMapping("/bookedTickets")
public class BookedTicketsController {

    private BookingFacade bookingFacade;

    @Autowired
    public void setBookingFacade(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<InputStreamResource> getBookedTicketsByUserId(@PathVariable long userId) {
        User user = bookingFacade.getUserById(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(user, 0, 0);

        if (bookedTickets.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayInputStream bis = GeneratePdfUtil.ticketsReport(bookedTickets);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticketsreport.pdf");

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }
}
