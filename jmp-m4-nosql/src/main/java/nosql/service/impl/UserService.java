package nosql.service.impl;

import nosql.domain.Sport;
import nosql.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nosql.repository.api.IUserRepository;
import nosql.service.api.IUserService;

import java.util.List;

/**
 * UserService
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public String addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String addSportToUser(String userId, Sport sport) {
        User user = userRepository.findById(userId);
        if (user != null) {
            return userRepository.addSportToUser(userId, sport);
        }
        return "user with id " + userId + " not found";
    }

    @Override
    public List<User> findBySportName(String sportName) {
        return userRepository.findBySportName(sportName);
    }
}
