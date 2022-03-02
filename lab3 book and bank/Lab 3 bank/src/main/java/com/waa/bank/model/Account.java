package com.waa.bank.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    private String accountHolder;
    private int accountNumber;
    private double balance;

}
