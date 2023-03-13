package com.epam.jmp.jmpm7springboot.repository;

import com.epam.jmp.jmpm7springboot.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * PersonRepository
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByLastName(String lastName);
    Person findById(long id);
}
