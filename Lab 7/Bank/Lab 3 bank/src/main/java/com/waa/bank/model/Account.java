package com.waa.bank.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Account {

    @NonNull
    private String accountHolder;
    @NonNull
    @Id
    private int accountNumber;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();


    public Account(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount){
        Transaction transaction1 = new Transaction( amount, new Date(), "Amount and Date of deposit");
        transactions.add(transaction1);
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        this.balance = balance;
    }
    public void withdraw(double amount){
        Transaction transaction1 = new Transaction(amount, new Date(), "Amount and Date of withdraw");
        transactions.add(transaction1);
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance -= transaction.getAmount();
        }
        this.balance = balance;
    }
}
