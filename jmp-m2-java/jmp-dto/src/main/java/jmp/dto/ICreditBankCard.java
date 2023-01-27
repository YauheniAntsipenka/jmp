package jmp.dto;

/**
 * ICreditBankCard
 * Date: 01/28/2023
 *
 * @author Yauheni Antsipenka
 */
public interface ICreditBankCard {
    CreditBankCard createBankCard(String number, User user);
}
