package nosql.service.impl;

import nosql.domain.Sport;
import nosql.domain.User;
import nosql.exception.UserNotFoundException;
import nosql.repository.api.UserRepository;
import nosql.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findByEmail(String email) {
        List<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public void addSportToUser(String userId, Sport sport) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getSports() == null) {
                user.setSports(List.of(sport));
            } else {
                user.getSports().add(sport);
            }
            userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> findBySportName(String sportName) {
        return userRepository.findBySportName(sportName);
    }
}
