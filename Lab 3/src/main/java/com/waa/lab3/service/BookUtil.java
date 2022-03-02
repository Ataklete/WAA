package com.waa.lab3.service;

import com.waa.lab3.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookUtil {

    private List<Book> books = new ArrayList<>();

    public List<Book> getBook(){
        return books;
    }

    public boolean addBook(Book book){
        for(Book i : books)
        if(i.getIsbn() == (book.getIsbn()))
            return false;
       return books.add(book);
    }

    public Book findByISBN(int isbn){
        for (Book book : books){
            if (isbn == book.getIsbn())
                return book;
        }
        return null;
    }
    public Book findByAuthor(String author){
        for (Book book : books){
            if (book.getAuthor().equals(author))
                return book;
        }
        return null;
    }

    public void deleteBook(int isbn){
        for (Book book : books){
        if (isbn == book.getIsbn())
          books.remove(book);
        break;
        }
    }

    public void updateBook(Book book){
        for(Book bo : books) {
            if(book.getIsbn() == bo.getIsbn()) {
                books.remove(bo);
                books.add(book);
                break;
            }
        }
    }
}
