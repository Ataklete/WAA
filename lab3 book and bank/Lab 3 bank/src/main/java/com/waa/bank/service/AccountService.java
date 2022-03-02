package com.waa.bank.service;

import com.waa.bank.model.Account;
import com.waa.bank.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    private Account account;
    private Transaction transaction1;
    private List<Transaction> transactions = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    public void deposit(double amount){
        transaction1 = new Transaction( amount, new Date(), "Amount and Date of deposit");
        transactions.add(transaction1);
        CalculateBalance();
    }
    public void withdraw(double amount){
        transaction1 = new Transaction(amount, new Date(), "Amount and Date of withdraw");
        transactions.add(transaction1);
        CalculateBalance();
    }

    public void CalculateBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        account.setBalance(balance);
    }


    public boolean createAccount(int accountNumber, String accountHolder) {
        accounts.add(new Account("test",100,100.00));
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
}
