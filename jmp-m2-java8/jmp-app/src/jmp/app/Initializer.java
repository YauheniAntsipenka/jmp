package jmp.app;

import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Initializer
 * Date: 01/31/2023
 *
 * @author Yauheni Antsipenka
 */
public final class Initializer {

    private Initializer() {
    }

    public static List<User> getUsers() {
        var user1 = new User("Vasya", "Vasil'ev", LocalDate.of(1990, 11, 11));
        var user2 = new User("Petya", "Petrov", LocalDate.of(1989, 2, 3));
        var user3 = new User("Valera", "Valer'ev", LocalDate.of(1999, 3, 7));
        return List.of(user1, user2, user3);
    }

    public static List<BankCard> getBankCards() {
        var bank = Initializer.getBank();
        var bankCard1 = bank.createBankCard(Initializer.getUsers().get(0), BankCardType.CREDIT);
        var bankCard2 = bank.createBankCard(Initializer.getUsers().get(1), BankCardType.DEBIT);
        var bankCard3 = bank.createBankCard(Initializer.getUsers().get(2), BankCardType.DEBIT);
        return List.of(bankCard1, bankCard2, bankCard3);
    }

    public static BankImpl getBank() {
        return new BankImpl();
    }

    public static ServiceImpl getService(List<BankCard> bankCards) {
        ServiceImpl service = new ServiceImpl();
        Initializer.getUsers().forEach(service::addUser);
        bankCards.forEach(service::subscribe);
        return service;
    }
}
