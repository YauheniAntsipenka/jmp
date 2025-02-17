package com.epam.jmp.spring.mvc.dao.storage.impl;

import com.epam.jmp.spring.mvc.dao.storage.Storage;
import com.epam.jmp.spring.mvc.model.impl.TicketImpl;
import com.epam.jmp.spring.mvc.model.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * TicketsStorageImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class TicketsStorageImpl implements Storage<Ticket> {

    private static final String PATH = "init/tickets.json";

    @Override
    public Map<Long, Ticket> retrieveInitDataFromFile() {
        Map<Long, Ticket> ticketsMap = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Arrays.asList(objectMapper.readValue(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(PATH)).toURI()), TicketImpl[].class))
                .forEach(ticket -> ticketsMap.put(ticket.getId(), ticket));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return ticketsMap;
    }
}
