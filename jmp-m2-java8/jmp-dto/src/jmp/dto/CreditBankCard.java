package jmp.dto;

/**
 * CreditBankCard
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class CreditBankCard extends BankCard implements ICreditBankCard {
    public CreditBankCard(String number, User user) {
        super(number, user);
    }

    @Override
    public CreditBankCard createBankCard(String number, User user) {
        return new CreditBankCard(number, user);
    }
}
