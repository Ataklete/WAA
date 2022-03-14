package com.waa.bank.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {
    @NonNull
    private double amount;
    private Date date;
    private String description;

}
