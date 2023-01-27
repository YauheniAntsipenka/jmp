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
        return getAllUsers()
            .stream()
            .mapToLong(Service::getAge)
            .average()
            .orElse(0);
    }

    static boolean isPayableUser(User user) {
        return getAge(user) >= 18;
    }

    static Long getAge(User user) {
        return user.getBirthday().until(LocalDate.now(), ChronoUnit.YEARS);
    }
}
