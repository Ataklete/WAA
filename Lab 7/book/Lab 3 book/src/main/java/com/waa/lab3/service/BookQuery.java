package com.waa.lab3.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.waa.lab3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookQuery implements GraphQLQueryResolver {

    @Autowired
    private BookService bookService;

    public List<Book> getBook(){
        return bookService.getBook();
    }
    public Book findByISBN(int isbn){
        return bookService.findByISBN(isbn);
    }
    public Book findByAuthor(String author){
        return bookService.findByAuthor(author);
    }

}
