import jmp.bank.api.Bank;
import jmp.cloud.bank.impl.BankImpl;

module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    exports jmp.cloud.bank.impl;
    provides Bank with BankImpl;
}
