package com.epam.jmp.spring.core.dao;

import com.epam.jmp.spring.core.model.User;

import java.util.Map;

/**
 * UserDao
 * Date: 03/22/2023
 *
 * @author Yauheni Antsipenka
 */
public interface UserDao extends CRUDDao<User> {
    Map<Long, User> getUsersMap();
}
