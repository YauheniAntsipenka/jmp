package com.epam.jmp.messaging.rest.controller;

import com.epam.jmp.messaging.dto.Event;
import com.epam.jmp.messaging.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * EventController
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public String createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return String.format("Message to create new event (%s) was sent to topic", event);
    }

    @PutMapping("/update")
    public String updateEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
        return String.format("Message to update event (%s) was sent to topic", event);
    }

    @DeleteMapping("/{id}")
    public String deleteEventById(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return String.format("Message to delete event with id %s was sent to topic", id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        Event event = eventService.findEvent(id);
        if (event == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(event);
    }

    @GetMapping
    public List<Event> findAllEvents() {
       return eventService.findAllEvents();
    }
}
