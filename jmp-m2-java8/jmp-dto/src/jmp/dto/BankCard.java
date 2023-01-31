package jmp.dto;

import java.util.StringJoiner;

/**
 * BankCard
 * Date: 01/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class BankCard {

    private String number;
    private User user;

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    public BankCard(String number, User user) {
        this.number = number;
        this.user = user;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BankCard.class.getSimpleName() + "[", "]")
            .add("number='" + number + "'")
            .add("user=" + user)
            .toString();
    }
}
