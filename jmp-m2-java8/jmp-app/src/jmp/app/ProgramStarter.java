package jmp.app;

import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.BankCardNumberNotFoundException;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCardType;
import jmp.dto.User;
import jmp.service.api.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * ProgramStarter
 * Date: 01/31/2023
 *
 * @author Yauheni Antsipenka
 */
public class ProgramStarter {

    private static final String NOT_FOUND_BANK_CARD_NUMBER = "`not_found_number`";

    public static void start() {
        List<User> users = Initializer.getUsers();
        BankImpl bank = Initializer.getBank();
        ServiceImpl service = Initializer.getService();

        var bankCard1 = bank.createBankCard(users.get(0), BankCardType.CREDIT);
        service.subscribe(bankCard1);
        var bankCard2 = bank.createBankCard(users.get(1), BankCardType.DEBIT);
        service.subscribe(bankCard2);
        var bankCard3 = bank.createBankCard(users.get(2), BankCardType.DEBIT);
        service.subscribe(bankCard3);

        service.addUser(users.get(0));
        service.addUser(users.get(1));
        service.addUser(users.get(2));

        service.getAllUsers().forEach(System.out::println);
        service.getSubscriptionByBankCardNumber(bankCard1.getNumber())
            .ifPresent(System.out::println);
        service.getSubscriptionByBankCardNumber(bankCard2.getNumber())
            .ifPresent(System.out::println);
        try {
            service.getSubscriptionByBankCardNumber(NOT_FOUND_BANK_CARD_NUMBER);
        } catch (BankCardNumberNotFoundException e) {
            System.out.printf("Error %s%n", e.getMessage());
        }

        System.out.printf("Average user age = %s%n", service.getAverageUsersAge());
        System.out.printf("User %s years, isPayable: %s%n", Service.getAge(users.get(1)),
            Service.isPayableUser(users.get(1)));

        var dateNow = LocalDate.now();
        System.out.printf("All subscriptions is equal %s: %s%n", dateNow,
            service.getAllSubscriptionsByCondition(subscription -> subscription.getStartDate().isEqual(dateNow)));
    }
}
