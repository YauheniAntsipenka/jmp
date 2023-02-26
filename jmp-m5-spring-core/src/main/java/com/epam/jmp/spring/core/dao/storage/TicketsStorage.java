package com.epam.jmp.spring.core.dao.storage;

import com.epam.jmp.spring.core.model.Ticket;

import java.util.Map;

/**
 * TicketsStorage
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface TicketsStorage {
    Map<Long, Ticket> retrieveInitDataFromFile();
}
