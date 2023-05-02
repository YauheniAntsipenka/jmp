package com.epam.jmp.spring.core.service;

import com.epam.jmp.spring.core.model.User;

import java.util.List;

/**
 * UserService
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface UserService {

    User findUserById(long userId);
    User findUserByEmail(String email);
    List<User> findUsersByName(String name, int pageSize, int pageNum);
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(long userId);
}
