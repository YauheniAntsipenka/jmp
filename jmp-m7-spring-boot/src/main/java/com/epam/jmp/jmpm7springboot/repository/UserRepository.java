package com.epam.jmp.jmpm7springboot.repository;

import com.epam.jmp.jmpm7springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserRepository
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u JOIN Role r ON r.id = u.role WHERE login = ?1")
    User findByLogin(String login);
}
