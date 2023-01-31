package jmp.app;

import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.BankCardNumberNotFoundException;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCardType;
import jmp.dto.User;
import jmp.service.api.Service;

import java.time.LocalDate;

/**
 * Main
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) {

        var user1 = new User("Vasya", "Vasil'ev", LocalDate.of(1990, 11, 11));
        var user2 = new User("Petya", "Petrov", LocalDate.of(1989, 2, 3));
        var user3 = new User("Valera", "Valer'ev", LocalDate.of(1999, 3, 7));

        var bank = new BankImpl();
        var service = new ServiceImpl();
        var bankCard1 = bank.createBankCard(user1, BankCardType.CREDIT);
        service.subscribe(bankCard1);
        var bankCard2 = bank.createBankCard(user2, BankCardType.DEBIT);
        service.subscribe(bankCard2);
        var bankCard3 = bank.createBankCard(user3, BankCardType.DEBIT);
        service.subscribe(bankCard3);

        service.addUser(user1);
        service.addUser(user2);
        service.addUser(user3);

        service.getAllUsers().forEach(System.out::println);
        service.getSubscriptionByBankCardNumber(bankCard1.getNumber())
            .ifPresent(System.out::println);
        service.getSubscriptionByBankCardNumber(bankCard2.getNumber())
            .ifPresent(System.out::println);
        try {
            service.getSubscriptionByBankCardNumber("`not_found_number`");
        } catch (BankCardNumberNotFoundException e) {
            System.out.printf("Error %s%n", e.getMessage());
        }

        System.out.printf("Average user age = %s%n", service.getAverageUsersAge());
        System.out.printf("User %s years, isPayable: %s%n", Service.getAge(user1), Service.isPayableUser(user1));

        var dateNow = LocalDate.now();
        System.out.printf("All subscriptions is equal %s: %s%n", dateNow,
            service.getAllSubscriptionsByCondition(subscription -> subscription.getStartDate().isEqual(dateNow)));
    }
}
