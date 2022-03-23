package com.waa.bank.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class TransactionDTO {
    @NonNull
    private double amount;
    private Date date;
    private String description;

}
