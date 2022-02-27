package com.waa.lab3.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    private int isbn;
    private String author;
    private String title;
    private double price;


}
