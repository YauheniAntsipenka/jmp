package com.epam.jmp.spring.core.dao.storage;

import com.epam.jmp.spring.core.model.Event;

import java.util.Map;

/**
 * Events
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventsStorage {
    Map<Long, Event> retrieveInitDataFromFile();
}
