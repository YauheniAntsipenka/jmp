package nosql.service.api;

import nosql.domain.Sport;
import nosql.domain.User;

import java.util.List;

/**
 * UserService
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
public interface UserService {

    User findById(String id);
    User addUser(User user);
    List<User> findByEmail(String email);
    void addSportToUser(String userId, Sport sport);
    List<User> findBySportName(String sportName);
}
