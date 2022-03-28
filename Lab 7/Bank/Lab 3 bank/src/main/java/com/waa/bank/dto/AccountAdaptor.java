package com.waa.bank.dto;

import com.waa.bank.model.Account;

public class AccountAdaptor {

    public static Account getAccount(AccountDTO accountDTO){
        Account account = new Account();
        if (accountDTO != null) {
            account = new Account(accountDTO.getAccountHolder(),
                    accountDTO.getAccountNumber(),
                    accountDTO.getBalance());
        }
        return account;
    }

    public static AccountDTO getAccountDTO(Account account){
        AccountDTO contactDto = new AccountDTO();
        if (account != null) {
            contactDto = new AccountDTO(account.getAccountHolder(),
                    account.getAccountNumber(),
                    account.getBalance());
        }
        return contactDto;
    }
}
