package com.epam.jmp.spring.core.dao;

import com.epam.jmp.spring.core.model.Ticket;

import java.util.Map;

/**
 * TicketDao
 * Date: 03/22/2023
 *
 * @author Yauheni Antsipenka
 */
public interface TicketDao extends CRUDDao<Ticket> {
    Map<Long, Ticket> getTicketsMap();
}
