package nosql.repository.api;

import nosql.domain.User;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
@Repository
@Collection("users")
public interface UserRepository extends CouchbaseRepository<User, String> {

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND email = $1")
    List<User> findByEmail(String email);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND ANY sport IN sports SATISFIES sport.sportName = $1 END")
    List<User> findBySportName(String sportName);
}
