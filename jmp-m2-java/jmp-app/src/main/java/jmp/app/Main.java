package jmp.app;

import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCardType;
import jmp.dto.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Main
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        initLogger();

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

        service.getAllUsers().forEach(user -> LOGGER.info(user));
        service.getSubscriptionByBankCardNumber(bankCard1.getNumber())
            .ifPresent(subscription -> LOGGER.info(subscription));
        service.getSubscriptionByBankCardNumber(bankCard2.getNumber())
            .ifPresent(subscription -> LOGGER.info(subscription));
    }

    private static void initLogger() {
        PropertyConfigurator.configure(Main.class.getResource("/log4j.properties"));
    }
}
