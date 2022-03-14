package com.waa.bank.service;

import com.waa.bank.model.Account;
import com.waa.bank.model.Transaction;
import com.waa.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    Transaction transaction;

    public boolean createAccount(int accountNumber, String accountHolder) {
        return repository.createAccount(accountNumber,accountHolder);
    }

    public Account getAccount(int accountNumber) {
        return repository.getAccount(accountNumber);
    }

    public String removeAccount(int accountNumber) {
       return repository.removeAccount(accountNumber);
    }

    public String deposit(int accountNumber, double amount) {
        Account account = repository.getAccount(accountNumber);
        if (account == null) {
            return "There is no account has an account number equal to " + accountNumber;
        }
        repository.deposit(account,amount);
        return "successful";
    }

    public String withdraw(int accountNumber, double amount) {
        Account account = repository.getAccount(accountNumber);
        if (account == null) {
            return "There is no account has an account number equal to " + accountNumber;
        }
        repository.withdraw(account,amount);
        return "successful";
    }
}
