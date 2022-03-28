package com.waa.bank.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.waa.bank.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountQuery implements GraphQLQueryResolver {

    @Autowired
    private AccountService accountService;

    public AccountDTO getAccount(int accountNumber) {
        return accountService.getAccount(accountNumber);
    }
}
