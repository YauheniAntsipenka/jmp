package jmp.cloud.bank.impl;

import jmp.bank.api.Bank;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.User;

import java.util.Arrays;
import java.util.Objects;

/**
 * BankImpl
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        return generate(bankCardType, String.valueOf(1 + (int)(Math.random() * 100)), user);
    }

    public static BankCard generate(BankCardType bankCardType, String number, User user) {
        return Objects.requireNonNull(Arrays.stream(BankCardType.values())
            .filter(bankCardType1 -> bankCardType1.getName().equals(bankCardType.getName()))
            .findAny().orElse(null)).getBankCardGenerator().generate(number, user);
    }
}
