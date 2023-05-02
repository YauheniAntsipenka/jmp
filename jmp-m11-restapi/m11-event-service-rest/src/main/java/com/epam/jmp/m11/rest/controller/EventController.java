package com.epam.jmp.m11.rest.controller;

import com.epam.jmp.m11.dto.Event;
import com.epam.jmp.m11.service.api.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * Date: 04/10/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@RequestMapping("/api/v1/events")
@ApiOperation("Events API")
public class EventController {

    @Autowired
    private EventService eventService;

    @ApiOperation(value = "Create new event", notes = "Returns a response entity with event")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully created"),
        @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);

        if (createdEvent == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Update existed event", notes = "Returns a response entity with event")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated"),
        @ApiResponse(code = 400, message = "Bad request")
    })
    @PutMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(event);

        if (updatedEvent == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "Find event by provided id", notes = "Returns a response entity with event")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Response entity with event"),
        @ApiResponse(code = 404, message = "Entity was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        Event event = eventService.findEvent(id);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(event);
    }

    @ApiOperation(value = "Delete event by provided id")
    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @ApiOperation(value = "Find all events in db", notes = "Returns a response entity with list of events")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Response entity with list of events")
    })
    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @ApiOperation(value = "Find all events in db by title", notes = "Returns a response entity with list of events")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Response entity with list of events")
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Event>> findAllEventsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(eventService.findAllEventsByTitle(title));
    }
}
