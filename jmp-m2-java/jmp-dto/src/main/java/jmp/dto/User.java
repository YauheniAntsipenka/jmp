package jmp.dto;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * User
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class User {

    private String name;
    private String surname;
    private LocalDate birthday;

    public User(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
            .add("name='" + name + "'")
            .add("surname='" + surname + "'")
            .add("birthday=" + birthday)
            .toString();
    }
}
