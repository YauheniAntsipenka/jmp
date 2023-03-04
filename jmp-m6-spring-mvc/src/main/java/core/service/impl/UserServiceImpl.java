package core.service.impl;

import core.dao.impl.UserDao;
import core.model.User;
import core.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UserServiceImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(long userId) {
        return userDao.get(userId).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getAll()
            .stream()
            .filter(user -> Objects.equals(user.getEmail(), email))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getAll()
            .stream()
            .filter(user -> Objects.equals(user.getName(), name))
            .collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDao.delete(userId);
    }
}
