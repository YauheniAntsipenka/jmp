package com.epam.jmp.spring.core.dao.storage.impl;

import com.epam.jmp.spring.core.dao.storage.Storage;
import com.epam.jmp.spring.core.exception.RetrieveDataException;
import com.epam.jmp.spring.core.model.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
            objectMapper.registerModule(new JavaTimeModule());
            Arrays.asList(objectMapper.readValue(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(PATH)).toURI()), Ticket[].class))
                .forEach(ticket -> ticketsMap.put(ticket.getId(), ticket));
        } catch (IOException | URISyntaxException e) {
            throw new RetrieveDataException(e);
        }
        return ticketsMap;
    }
}
