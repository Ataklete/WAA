package com.waa.lab3.repository;

import com.waa.lab3.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    List<Book> books = new ArrayList<>();
//    Book book1 = new Book(1,"Book1Author","Book1",10.00f);
//    List<Book> books = Arrays.asList(book1);

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

    public String deleteBook(int isbn){
        for (Book book : books){
            if (isbn == book.getIsbn())
                books.remove(book);
            break;
        }
        return null;
    }

    public String updateBook(Book book){
        for(Book bo : books) {
            if(book.getIsbn() == bo.getIsbn()) {
                books.remove(bo);
                books.add(book);
                break;
            }
        }
        return null;
    }
}
