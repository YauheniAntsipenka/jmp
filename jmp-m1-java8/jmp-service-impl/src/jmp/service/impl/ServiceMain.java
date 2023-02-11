package jmp.service.impl;

import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.BankCardNumberNotFoundException;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCardType;
import jmp.dto.User;

import java.time.LocalDate;
import java.util.ServiceLoader;

/**
 * ServiceMain
 * Date: 01/28/2023
 *
 * @author Yauheni Antsipenka
 */
public class ServiceMain {

    public static void main(String[] args) {
        ServiceLoader<ServiceImpl> serviceImpl = ServiceLoader.load(ServiceImpl.class);
        ServiceLoader<BankImpl> bankImpl = ServiceLoader.load(BankImpl.class);

        serviceImpl.forEach(service -> {
            try {
                service.getSubscriptionByBankCardNumber("not_found_number");
            } catch (BankCardNumberNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        bankImpl.forEach(bank -> {
            bank.createBankCard(new User("Petr", "Petrov", LocalDate.of(1955, 1, 1)), BankCardType.DEBIT);
        });
    }
}
