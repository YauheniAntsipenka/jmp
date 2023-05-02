package com.epam.jmp.spring.mvc.dao.storage;

import java.util.Map;

/**
 * Events
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface Storage<T> {
    Map<Long, T> retrieveInitDataFromFile();
}
