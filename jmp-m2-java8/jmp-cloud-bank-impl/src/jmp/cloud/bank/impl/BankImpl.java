package jmp.cloud.bank.impl;

import jmp.bank.api.Bank;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.User;

/**
 * BankImpl
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        return BankCardType.generate(bankCardType, String.valueOf(1 + (int)(Math.random() * 100)), user);
    }
}
