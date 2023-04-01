package com.epam.jmp.messaging.rest.controller;

import com.epam.jmp.messaging.dto.Event;
import com.epam.jmp.messaging.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        if (createdEvent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        if (updatedEvent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        Event event = eventService.findEvent(id);
        if (event == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping
    public List<Event> findAllEvents() {
       return eventService.findAllEvents();
    }
}
