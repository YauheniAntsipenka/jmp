package nosql.service.api;

import nosql.domain.Sport;
import nosql.domain.User;

import java.util.List;

/**
 * IUserService
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
public interface IUserService {

    User findById(String id);
    String addUser(User user);
    List<User> findByEmail(String email);
    String addSportToUser(String userId, Sport sport);
    List<User> findBySportName(String sportName);
}
