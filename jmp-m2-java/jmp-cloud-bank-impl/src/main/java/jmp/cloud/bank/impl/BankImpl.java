package jmp.cloud.bank.impl;

import jmp.bank.api.Bank;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.CreditBankCard;
import jmp.dto.DebitBankCard;
import jmp.dto.User;

import java.util.Random;

/**
 * BankImpl
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        var number = 1 + (int)(Math.random() * 100);
        BankCard bankCard = null;
        switch (bankCardType) {
            case CREDIT:
                bankCard = new CreditBankCard(String.valueOf(number), user);
                break;
            case DEBIT:
                bankCard = new DebitBankCard(String.valueOf(number), user);
                break;
        }
        return bankCard;
    }
}
