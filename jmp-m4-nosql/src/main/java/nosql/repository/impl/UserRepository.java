package nosql.repository.impl;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import nosql.domain.Sport;
import nosql.domain.User;
import nosql.repository.api.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * UserRepository
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private Bucket bucket;
    @Autowired
    private Cluster cluster;

    @Override
    public User findById(String id) {
        return bucket.collection("users").get(id).contentAs(User.class);
    }

    @Override
    public List<User> findByEmail(String email) {
        return cluster.query("SELECT users.* FROM `users`._default.users WHERE email = \"" + email + "\"")
            .rowsAs(User.class);
    }

    @Override
    public String addUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        bucket.collection("users").insert(userId, user);
        return userId;
    }

    @Override
    public String addSportToUser(String userId, Sport sport) {
        User user = bucket.collection("users").get(userId).contentAs(User.class);
        if (user.getSports() == null) {
            user.setSports(List.of(sport));
        } else {
            user.getSports().add(sport);
        }
        return bucket.collection("users").upsert(userId, user).toString();
    }

    @Override
    public List<User> findBySportName(String sportName) {
        return cluster.query("SELECT u.* FROM `users`._default.users u\n" +
            "UNNEST u.sports AS sp\n" +
            "WHERE sp.sportName = \"" + sportName + "\"").rowsAs(User.class);
    }
}
