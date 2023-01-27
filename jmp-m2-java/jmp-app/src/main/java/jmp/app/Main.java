package jmp.app;

import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCardType;
import jmp.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Main
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        var user1 = new User("Vasya", "Vasil'ev", LocalDate.of(1990, 11, 11));
        var user2 = new User("Petya", "Petrov", LocalDate.of(1989, 2, 3));

        var bank = new BankImpl();
        var service = new ServiceImpl();
        var bankCard1 = bank.createBankCard(user1, BankCardType.CREDIT);
        service.subscribe(bankCard1);
        var bankCard2 = bank.createBankCard(user2, BankCardType.DEBIT);
        service.subscribe(bankCard2);

        service.addUser(user1);
        service.addUser(user2);

        service.getAllUsers().forEach(user -> LOGGER.info(user.toString()));
        service.getSubscriptionByBankCardNumber(bankCard1.getNumber())
            .ifPresent(subscription -> LOGGER.info(subscription.toString()));
        service.getSubscriptionByBankCardNumber(bankCard2.getNumber())
            .ifPresent(subscription -> LOGGER.info(subscription.toString()));
    }
}
