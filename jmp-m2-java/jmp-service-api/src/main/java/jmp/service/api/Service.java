package jmp.service.api;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    default double getAverageUsersAge() {
        LocalDate dateNow = LocalDate.now();
        return getAllUsers()
            .stream()
            .mapToLong(user -> user.getBirthday().until(dateNow, ChronoUnit.YEARS))
            .average()
            .orElse(0);
    }
}
