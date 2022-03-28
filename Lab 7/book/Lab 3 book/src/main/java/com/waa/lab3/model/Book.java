package com.waa.lab3.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @NonNull
    private int isbn;
    @NonNull
    private String author;
    @NonNull
    private String title;
    @NonNull
    private double price;


}
