package jmp.dto;

/**
 * DebitBankCard
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class DebitBankCard extends BankCard implements IDebitBankCard {
    public DebitBankCard(String number, User user) {
        super(number, user);
    }

    @Override
    public DebitBankCard createBankCard(String number, User user) {
        return new DebitBankCard(number, user);
    }
}
