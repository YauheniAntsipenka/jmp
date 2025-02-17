package com.epam.jmp.spring.mvc.dao.impl;

import com.epam.jmp.spring.mvc.dao.storage.Storage;
import com.epam.jmp.spring.mvc.dao.Dao;
import com.epam.jmp.spring.mvc.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * TicketDao
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class TicketDao implements Dao<Ticket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDao.class);

    private final Map<Long, Ticket> ticketsMap = new HashMap<>();
    private Storage<Ticket> storage;

    @Override
    public Optional<Ticket> get(long id) {
        return Optional.ofNullable(ticketsMap.get(id));
    }

    @Override
    public Collection<Ticket> getAll() {
        return ticketsMap.values();
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (ticketsMap.keySet().stream().anyMatch(id -> id == ticket.getId())) {
            LOGGER.info("Ticket with id {} exists in map", ticket.getId());
            return null;
        }
        ticketsMap.put(ticket.getId(), ticket);
        LOGGER.info("Ticket with id {} was created", ticket.getId());
        return ticket;
    }

    @Override
    public Ticket update(Ticket ticket) {
        if (ticketsMap.keySet().stream().noneMatch(id -> id == ticket.getId())) {
            LOGGER.info("Ticket with id {} not exists in map", ticket.getId());
            return null;
        }
        Ticket ticketToUpdate = ticketsMap.get(ticket.getId());
        ticketToUpdate.setCategory(ticket.getCategory());
        ticketToUpdate.setEventId(ticket.getEventId());
        ticketToUpdate.setPlace(ticket.getPlace());
        ticketToUpdate.setUserId(ticket.getUserId());
        ticketsMap.put(ticket.getId(), ticketToUpdate);
        LOGGER.info("Ticket with id {} was updated", ticket.getId());
        return ticket;
    }

    @Override
    public boolean delete(long ticketId) {
        if (ticketsMap.keySet().stream().noneMatch(id -> id == ticketId)) {
            LOGGER.info("Ticket with id {} not exists in map", ticketId);
            return false;
        }
        ticketsMap.remove(ticketId);
        LOGGER.info("Ticket with id {} was removed", ticketId);
        return true;
    }

    public Map<Long, Ticket> getTicketsMap() {
        return ticketsMap;
    }

    public void setStorage(Storage<Ticket> storage) {
        this.storage = storage;
    }

    private void init() {
        ticketsMap.putAll(storage.retrieveInitDataFromFile());
    }
}
