package com.waa.bank.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.waa.bank.dto.AccountDTO;
import com.waa.bank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMutation implements GraphQLMutationResolver {

    @Autowired
    private AccountService accountService;

    public Account createAccount(int accountNumber, String accountHolder) {
        return accountService.createAccount(accountNumber, accountHolder);
    }

    public void removeAccount(int accountNumber) {
        accountService.removeAccount(accountNumber);
    }

    public AccountDTO deposit(int accountNumber, double amount) {
        return accountService.deposit(accountNumber,amount);

    }

    public AccountDTO withdraw(int accountNumber, double amount) {
        return accountService.withdraw(accountNumber,amount);
    }
}
