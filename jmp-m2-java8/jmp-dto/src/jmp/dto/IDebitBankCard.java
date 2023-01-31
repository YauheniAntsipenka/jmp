package jmp.dto;

/**
 * IDebitBankCard
 * Date: 01/28/2023
 *
 * @author Yauheni Antsipenka
 */
public interface IDebitBankCard {
    DebitBankCard createBankCard(String number, User user);
}
