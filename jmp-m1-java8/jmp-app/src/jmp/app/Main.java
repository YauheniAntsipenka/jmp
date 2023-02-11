package jmp.app;

import jmp.cloud.service.impl.BankCardNumberNotFoundException;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCard;
import jmp.dto.User;
import jmp.service.api.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

/**
 * Main
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final String NOT_FOUND_BANK_CARD_NUMBER = "`not_found_number`";
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        List<User> users = Initializer.getUsers();
        List<BankCard> bankCards = Initializer.getBankCards();
        ServiceImpl service = Initializer.getService(bankCards);

        bankCards.forEach(bankCard -> {
            service.subscribe(bankCard);
            service.getSubscriptionByBankCardNumber(bankCard.getNumber())
                .ifPresent(subscription -> LOGGER.info(String.valueOf(subscription)));
        });
        service.getAllUsers().forEach(user -> LOGGER.info(String.valueOf(user)));
        try {
            service.getSubscriptionByBankCardNumber(NOT_FOUND_BANK_CARD_NUMBER);
        } catch (BankCardNumberNotFoundException e) {
            LOGGER.error("Error {}", e.getMessage());
        }

        LOGGER.info("Average user age = {}", service.getAverageUsersAge());
        LOGGER.info("User {} years, isPayable: {}", Service.getAge(users.get(1)), Service.isPayableUser(users.get(1)));

        var dateNow = LocalDate.now();
        LOGGER.info("All subscriptions is equal {}: {}", dateNow,
            service.getAllSubscriptionsByCondition(subscription -> subscription.getStartDate().isEqual(dateNow)));
    }
}
