package jmp.dto;

import java.util.Arrays;
import java.util.Objects;

/**
 * BankCardType
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public enum BankCardType {
    CREDIT("CREDIT", CreditBankCard::new),
    DEBIT("DEBIT", DebitBankCard::new);

    private final String name;
    private final BankCardGenerator bankCardGenerator;

    BankCardType(String name, BankCardGenerator bankCardGenerator) {
        this.name = name;
        this.bankCardGenerator = bankCardGenerator;
    }

    public String getName() {
        return name;
    }

    public BankCardGenerator getBankCardGenerator() {
        return bankCardGenerator;
    }

    public static BankCard generate(BankCardType bankCardType, String number, User user) {
        return Objects.requireNonNull(Arrays.stream(BankCardType.values())
            .filter(bankCardType1 -> bankCardType1.getName().equals(bankCardType.getName()))
            .findAny().orElse(null)).getBankCardGenerator().generate(number, user);
    }
}
