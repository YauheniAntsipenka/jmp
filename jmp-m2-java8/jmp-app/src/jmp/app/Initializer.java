package jmp.app;

import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Initializer
 * Date: 01/31/2023
 *
 * @author Yauheni Antsipenka
 */
public class Initializer {

    public static List<User> getUsers() {
        var user1 = new User("Vasya", "Vasil'ev", LocalDate.of(1990, 11, 11));
        var user2 = new User("Petya", "Petrov", LocalDate.of(1989, 2, 3));
        var user3 = new User("Valera", "Valer'ev", LocalDate.of(1999, 3, 7));
        return List.of(user1, user2, user3);
    }

    public static BankImpl getBank() {
        return new BankImpl();
    }

    public static ServiceImpl getService() {
        return new ServiceImpl();
    }
}
