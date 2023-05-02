package com.epam.jmp.messaging;

import com.epam.jmp.messaging.dto.Event;

/**
 * EventMessaging
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventMessaging {

    void createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(Long id);
}
