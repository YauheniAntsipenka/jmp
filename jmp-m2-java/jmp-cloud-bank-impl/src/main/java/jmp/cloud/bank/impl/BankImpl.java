package jmp.cloud.bank.impl;

import jmp.bank.api.Bank;
import jmp.dto.*;

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
                ICreditBankCard creditBankCard = CreditBankCard::new;
                bankCard = creditBankCard.createBankCard(String.valueOf(number), user);
                break;
            case DEBIT:
                IDebitBankCard debitBankCard = DebitBankCard::new;
                bankCard = debitBankCard.createBankCard(String.valueOf(number), user);
                break;
        }
        return bankCard;
    }
}
