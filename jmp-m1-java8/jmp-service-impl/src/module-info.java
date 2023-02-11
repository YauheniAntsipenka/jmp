module jmp.service.impl {
    uses jmp.cloud.service.impl.ServiceImpl;
    uses jmp.cloud.bank.impl.BankImpl;
    requires jmp.cloud.service.impl;
    requires jmp.cloud.bank.impl;
    requires jmp.dto;
}
