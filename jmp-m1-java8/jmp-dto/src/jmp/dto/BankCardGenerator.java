package jmp.dto;

/**
 * BankCardGenerator
 * Date: 01/31/2023
 *
 * @author Yauheni Antsipenka
 */
@FunctionalInterface
public interface BankCardGenerator {
    BankCard generate(String number, User user);
}
