package com.epam.jmp.spring.mvc.controller;

import com.epam.jmp.spring.mvc.exception.TicketParseException;
import com.epam.jmp.spring.mvc.facade.BookingFacade;
import com.epam.jmp.spring.mvc.model.Event;
import com.epam.jmp.spring.mvc.model.Ticket;
import com.epam.jmp.spring.mvc.model.User;
import com.epam.jmp.spring.mvc.model.impl.EventImpl;
import com.epam.jmp.spring.mvc.model.impl.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

/**
 * BookingFacadeController
 * Date: 03/04/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@RequestMapping("/booking")
public class BookingFacadeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingFacadeController.class);

    private BookingFacade bookingFacade;

    @Autowired
    public void setBookingFacade(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping(path = "/event/{eventId}")
    public ModelAndView getEventById(@PathVariable Integer eventId, ModelAndView modelAndView) {
        Event event = bookingFacade.getEventById(eventId);

        if (event != null) {
            modelAndView.addObject("event", event);
            modelAndView.setViewName("event/event.html");
        } else {
            modelAndView.setViewName("event/notFound.html");
        }

        return modelAndView;
    }

    @GetMapping(path = "/event/title/{title}")
    public ModelAndView getEventsByTitle(@PathVariable String title, ModelAndView modelAndView) {
        List<Event> events = bookingFacade.getEventsByTitle(title, 0, 0);

        if (events.size() == 0) {
            modelAndView.setViewName("event/notFound.html");
        } else {
            modelAndView.addObject("events", events);
            modelAndView.setViewName("event/events.html");
        }

        return modelAndView;
    }

    @GetMapping(path = "/event/day/{day}")
    public ModelAndView getEventsForDay(
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day, ModelAndView modelAndView) {
        List<Event> events = bookingFacade.getEventsForDay(day, 0, 0);

        if (events.size() == 0) {
            modelAndView.setViewName("event/notFound.html");
        } else {
            modelAndView.addObject("events", events);
            modelAndView.setViewName("event/events.html");
        }

        return modelAndView;
    }

    @PostMapping(path = "/event/create", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createEvent(@RequestBody EventImpl event) {
        Event createdEvent = bookingFacade.createEvent(event);

        if (createdEvent == null) {
            return ResponseEntity.badRequest().body(String.format("Event with id %s already created", event.getId()));
        }

        return new ResponseEntity<>(String.format("Event with id %s was created", createdEvent.getId()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/event/update/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEvent(@RequestBody EventImpl event) {
        Event updatedEvent = bookingFacade.updateEvent(event);

        if (updatedEvent == null) {
            return ResponseEntity.badRequest().body(String.format("Event with id %s not exists", event.getId()));
        }

        return new ResponseEntity<>(String.format("Event with id %s was updated", updatedEvent.getId()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/event/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable long eventId) {
        boolean isDeleted = bookingFacade.deleteEvent(eventId);

        if (!isDeleted) {
            return ResponseEntity.badRequest().body(String.format("Event with id %s not exists", eventId));
        }

        return new ResponseEntity<>(String.format("Event with id %s was deleted", eventId), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}")
    public ModelAndView getUserById(@PathVariable Integer userId, ModelAndView modelAndView) {
        User user = bookingFacade.getUserById(userId);

        if (user != null) {
            modelAndView.addObject("user", user);
            modelAndView.setViewName("user/user.html");
        } else {
            modelAndView.setViewName("user/notFound.html");
        }

        return modelAndView;
    }

    @GetMapping(path = "/user/email/{email}")
    public ModelAndView getUserByEmail(@PathVariable String email, ModelAndView modelAndView) {
        User user = bookingFacade.getUserByEmail(email);

        if (user != null) {
            modelAndView.addObject("user", user);
            modelAndView.setViewName("user/user.html");
        } else {
            modelAndView.setViewName("user/notFound.html");
        }

        return modelAndView;
    }

    @PostMapping(path = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserImpl user) {
        User createdUser = bookingFacade.createUser(user);

        if (createdUser == null) {
            return ResponseEntity.badRequest().body(String.format("User with id %s already created", user.getId()));
        }

        return new ResponseEntity<>(String.format("User with id %s was created", createdUser.getId()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/user/update/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody UserImpl user) {
        User updatedUser = bookingFacade.updateUser(user);

        if (updatedUser == null) {
            return ResponseEntity.badRequest().body(String.format("User with id %s not exists", user.getId()));
        }

        return new ResponseEntity<>(String.format("User with id %s was updated", updatedUser.getId()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        boolean isDeleted = bookingFacade.deleteUser(userId);

        if (!isDeleted) {
            return ResponseEntity.badRequest().body(String.format("User with id %s not exists", userId));
        }

        return new ResponseEntity<>(String.format("User with id %s was deleted", userId), HttpStatus.OK);
    }

    @GetMapping(path = "/ticket/booked/{userId}")
    public ResponseEntity<String> getBookedTicketsByUserId(@PathVariable long userId) {
        User user = bookingFacade.getUserById(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(user, 0, 0);

        if (bookedTickets.size() == 0) {
            return ResponseEntity.ok().body(String.format("There are not booked tickets for user with id %s ", userId));
        }

        return new ResponseEntity<>(bookedTickets.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/ticket/booked/event/{eventId}")
    public ResponseEntity<String> getBookedTicketsByEventId(@PathVariable long eventId) {
        Event event = bookingFacade.getEventById(eventId);

        if (event == null) {
            return ResponseEntity.badRequest().body(String.format("Event with id %s not exists", eventId));
        }

        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(event, 0, 0);

        if (bookedTickets.size() == 0) {
            return ResponseEntity.ok().body(String.format("There are not booked tickets for event with id %s ", eventId));
        }

        return new ResponseEntity<>(bookedTickets.toString(), HttpStatus.OK);
    }

    @PutMapping(path = "/ticket/booked/cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable long ticketId) {
        boolean isCanceled = bookingFacade.cancelTicket(ticketId);

        if (!isCanceled) {
            return ResponseEntity.badRequest().body(String.format("Ticket with id %s not exists", ticketId));
        }

        return ResponseEntity.ok().body(String.format("Ticket with id %s was canceled", ticketId));
    }

    @GetMapping(path = "/ticket/preload")
    public void preloadTickets() {
        try {
            bookingFacade.preloadTickets();
            LOGGER.info("Tickets from xml file were successfully added to storage");
        } catch (TicketParseException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @GetMapping(path = "/npe")
    public void npe() {
        throw new NullPointerException();
    }

}
