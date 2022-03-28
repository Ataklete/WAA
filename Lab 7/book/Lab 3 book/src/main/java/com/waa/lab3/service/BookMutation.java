package com.waa.lab3.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.waa.lab3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookMutation implements GraphQLMutationResolver {

    @Autowired
    private BookService bookService;


    public boolean addBook(Book book){
        return bookService.addBook(book);
    }

    public String deleteBook(int isbn){
        return bookService.deleteBook(isbn);
    }

    public String updateBook(Book book){
        return bookService.updateBook(book);
    }
}
