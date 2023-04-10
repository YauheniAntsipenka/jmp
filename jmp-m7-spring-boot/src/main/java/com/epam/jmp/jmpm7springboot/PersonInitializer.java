package com.epam.jmp.jmpm7springboot;

import com.epam.jmp.jmpm7springboot.domain.Person;

import java.util.List;

/**
 * PersonInitializer
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
public class PersonInitializer {

    public static List<Person> init() {
        Person person1 = new Person("Ivan", "Ivanov");
        Person person2 = new Person("Petr", "Petrov");
        Person person3 = new Person("Person", "Personov");
        return List.of(person1, person2, person3);
    }
}
