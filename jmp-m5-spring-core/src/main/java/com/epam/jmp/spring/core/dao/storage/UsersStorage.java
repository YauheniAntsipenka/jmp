package com.epam.jmp.spring.core.dao.storage;

import com.epam.jmp.spring.core.model.User;

import java.util.Map;

/**
 * UsersStorage
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface UsersStorage {
    Map<Long, User> retrieveInitDataFromFile();
}
