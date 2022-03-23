package com.waa.bank.repository;

import com.waa.bank.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {
    private Account account;
    private List<Account> accounts = new ArrayList<>();

    public boolean createAccount(int accountNumber, String accountHolder) {
//        accounts.add(new Account("test",100,100.00));
        for (Account acc : accounts){
            if(acc.getAccountNumber() == accountNumber)
                return false;
        }
        account = new Account(accountHolder,accountNumber,0.0);
        accounts.add(account);
        return true;
    }

    public Account getAccount(int accountNumber) {
        for (Account acc : accounts){
            if(acc.getAccountNumber() == accountNumber)
                return acc;
        }
        return null;
    }

    public String removeAccount(int accountNumber) {
        for (Account book : accounts){
            if (accountNumber == book.getAccountNumber())
                accounts.remove(book);
            break;
        }
        return null;
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
    }
}

