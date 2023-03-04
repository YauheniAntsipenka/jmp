package core.service;

import core.model.User;

import java.util.List;

/**
 * UserService
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface UserService {

    User getUserById(long userId);
    User getUserByEmail(String email);
    List<User> getUsersByName(String name, int pageSize, int pageNum);
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(long userId);
}
