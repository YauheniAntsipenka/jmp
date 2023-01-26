package jmp.dto;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * Subscription
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Subscription {

    private BankCard bankcard;
    private LocalDate startDate;

    public Subscription(BankCard bankcard, LocalDate startDate) {
        this.bankcard = bankcard;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Subscription.class.getSimpleName() + "[", "]")
            .add("bankcard=" + bankcard)
            .add("startDate=" + startDate)
            .toString();
    }
}
