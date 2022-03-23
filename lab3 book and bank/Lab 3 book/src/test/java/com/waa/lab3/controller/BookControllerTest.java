package com.waa.lab3.controller;

import com.waa.lab3.model.Book;
import com.waa.lab3.service.BookService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    private BookService service;

    @InjectMocks
    private BookController controller;

    private Book book1;

    List<Book> books = new ArrayList<>();

    @BeforeEach
    void setUp() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/book";

        book1 = new Book(1,"Book1Author","Book1",10.00f);
        books.add(book1);
    }

    @Test
    void getAllBooks() {
        Mockito.when(service.getBook()).thenReturn(books);

        controller.getAllBooks();

        Mockito.verify(service).getBook();
    }

    @Test
    void addBook() {

        Mockito.when(service.addBook(book1)).thenReturn(true);
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.addBook(book1, result);

        Mockito.verify(service).addBook(book1);
    }

    @Test
    void updateBook() {

        Mockito.when(service.findByISBN(1)).thenReturn(book1);
        Mockito.when(service.updateBook(book1)).thenReturn("");
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.updateBook(book1, result);

        Mockito.verify(service).updateBook(book1);
    }

    @Test
    void deleteBook() {
        Mockito.when(service.findByISBN(1)).thenReturn(book1);
        Mockito.when(service.deleteBook(1)).thenReturn("");
        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.deleteBook(1, result);

        Mockito.verify(service).deleteBook(1);
    }

    @Test
    void getBookByIsbn() {
        Mockito.when(service.findByISBN(1)).thenReturn(book1);

        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.getBook(1, result);

        Mockito.verify(service).findByISBN(1);
    }

    @Test
    void searchBooks() {
        Mockito.when(service.findByAuthor("Book1Author")).thenReturn(book1);

        BindingResult result = new BeanPropertyBindingResult("", "");
        controller.searchBooks("Book1Author", result);

        Mockito.verify(service).findByAuthor("Book1Author");
    }
}