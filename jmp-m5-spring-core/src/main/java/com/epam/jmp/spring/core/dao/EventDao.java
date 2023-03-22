package com.epam.jmp.spring.core.dao;

import com.epam.jmp.spring.core.model.Event;

import java.util.Map;

/**
 * EventDao
 * Date: 03/22/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EventDao extends CRUDDao<Event> {
    Map<Long, Event> getEventsMap();
}
