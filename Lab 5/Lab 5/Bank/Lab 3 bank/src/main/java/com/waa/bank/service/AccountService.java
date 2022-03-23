package com.waa.bank.service;

import com.waa.bank.dto.AccountAdaptor;
import com.waa.bank.dto.AccountDTO;
import com.waa.bank.model.Account;
import com.waa.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    Account acc = new Account();

    AccountAdaptor adaptor = new AccountAdaptor();

    public Account createAccount(int accountNumber, String accountHolder) {
        AccountDTO account = new AccountDTO(accountHolder,accountNumber,0.0);
        Account account1 = adaptor.getAccount(account);
        return  repository.save(account1);
    }

    public AccountDTO getAccount(int accountNumber) {
        Account account = repository.findAccountByAccountNumber(accountNumber);
        return adaptor.getAccountDTO(account);
    }

    public void removeAccount(int accountNumber) {
        repository.deleteById(accountNumber);
    }

    public AccountDTO deposit(int accountNumber, double amount) {
        Account account = repository.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            return null;
        }
        acc.deposit(amount);
        acc = new Account(account.getAccountHolder(), account.getAccountNumber(), this.acc.getBalance());
        Account ret = repository.save(acc);
        AccountDTO dto = adaptor.getAccountDTO(ret);
        return dto;

    }

    public AccountDTO withdraw(int accountNumber, double amount) {
        Account account = repository.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            return null;
        }
        acc.withdraw(amount);
        acc = new Account(account.getAccountHolder(), account.getAccountNumber(), this.acc.getBalance());
        Account ret = repository.save(acc);
        AccountDTO dto = adaptor.getAccountDTO(ret);
        return dto;
    }
}
