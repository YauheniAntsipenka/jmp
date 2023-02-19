package nosql.repository.api;

import nosql.domain.Sport;
import nosql.domain.User;

import java.util.List;

/**
 * IUserRepository
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
public interface IUserRepository {

    User findById(String id);
    List<User> findByEmail(String email);
    String addUser(User user);
    String addSportToUser(String userId, Sport sport);
    List<User> findBySportName(String sportName);
}
