package jmp.bank.api;

import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.User;

/**
 * Bank
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public interface Bank {

    BankCard createBankCard(User user, BankCardType bankCardType);
}
