package jmp.cloud.service.impl;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * ServiceImpl
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class ServiceImpl implements Service {

    private final List<User> users = new ArrayList<>();
    private final Map<String, Subscription> bankCardNumberToSubscriptionMap = new HashMap<>();

    @Override
    public void subscribe(BankCard bankCard) {
        bankCardNumberToSubscriptionMap.put(bankCard.getNumber(), new Subscription(bankCard, LocalDate.now()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return Optional.ofNullable(Optional.ofNullable(bankCardNumberToSubscriptionMap.get(number))
            .orElseThrow(() -> new BankCardNumberNotFoundException(number + "not found")));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return bankCardNumberToSubscriptionMap.values().stream().filter(predicate).collect(Collectors.toList());
    }

    public void addUser(User user) {
        users.add(user);
    }
}
