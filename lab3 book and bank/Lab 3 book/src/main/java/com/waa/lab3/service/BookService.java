package com.waa.lab3.service;

import com.waa.lab3.model.Book;
import com.waa.lab3.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getBook(){
        return repository.getBook();
    }
    public boolean addBook(Book book){
        return repository.addBook(book);
    }

    public Book findByISBN(int isbn){
        return repository.findByISBN(isbn);
    }
    public Book findByAuthor(String author){
       return repository.findByAuthor(author);
    }

    public String deleteBook(int isbn){
       return repository.deleteBook(isbn);
    }

    public String updateBook(Book book){
        return repository.updateBook(book);
    }
}
