package com.waa.bank.dto;

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
public class AccountDTO {

    @NonNull
    private String accountHolder;
    @NonNull
    @Id
    private int accountNumber;
    private double balance;
    private List<TransactionDTO> transactionDTOS = new ArrayList<>();


    public AccountDTO(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount){
        TransactionDTO transactionDTO1 = new TransactionDTO( amount, new Date(), "Amount and Date of deposit");
        transactionDTOS.add(transactionDTO1);
        double balance = 0;
        for (TransactionDTO transactionDTO : transactionDTOS) {
            balance += transactionDTO.getAmount();
        }
        this.balance = balance;
    }
    public void withdraw(double amount){
        TransactionDTO transactionDTO1 = new TransactionDTO(amount, new Date(), "Amount and Date of withdraw");
        transactionDTOS.add(transactionDTO1);
        double balance = 0;
        for (TransactionDTO transactionDTO : transactionDTOS) {
            balance -= transactionDTO.getAmount();
        }
        this.balance = balance;
    }
}
