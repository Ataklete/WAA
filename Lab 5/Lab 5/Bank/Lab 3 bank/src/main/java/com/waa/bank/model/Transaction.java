package com.waa.bank.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Transaction {
    @NonNull
    private double amount;
    private Date date;
    private String description;

}
