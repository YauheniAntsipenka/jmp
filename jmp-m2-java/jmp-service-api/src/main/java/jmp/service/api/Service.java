package jmp.service.api;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;

import java.util.List;
import java.util.Optional;

/**
 * Service
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface Service {

    void subscribe(BankCard bankCard);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);
    List<User> getAllUsers();
}
