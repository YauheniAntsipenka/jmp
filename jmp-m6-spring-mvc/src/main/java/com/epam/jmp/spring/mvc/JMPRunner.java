package com.epam.jmp.spring.mvc;

import com.epam.jmp.spring.mvc.facade.impl.BookingFacadeImpl;
import com.epam.jmp.spring.mvc.model.impl.UserImpl;
import com.epam.jmp.spring.mvc.facade.BookingFacade;
import com.epam.jmp.spring.mvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Runner
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMPRunner.class);

    public static void runApp(ApplicationContext context) {
        BookingFacade bookingFacade = context.getBean(BookingFacadeImpl.class);
        LOGGER.info("Event with id 1: {}", bookingFacade.getEventById(1));

        LOGGER.info("Try to create new user: {}", bookingFacade.createUser(new UserImpl(4, "q", "q@q")));
        LOGGER.info("Try to find user with email {}. User: {}", "q@q", bookingFacade.getUserByEmail("q@q"));

        User userById = bookingFacade.getUserById(3);
        LOGGER.info("User with id 3: {}", userById);
        LOGGER.info("Booked tickets for user with id 3: {}", bookingFacade.getBookedTickets(userById, 1, 1));
    }
}
