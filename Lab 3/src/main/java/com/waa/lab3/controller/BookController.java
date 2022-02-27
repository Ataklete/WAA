package com.waa.lab3.controller;

import com.waa.lab3.model.Book;
import com.waa.lab3.model.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookUtil bookUtil;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> getAllBooks(){
        List<Book> books = bookUtil.getBook();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> addBook(@RequestBody Book book){
        bookUtil.addBook(book);
        return ResponseEntity.status(HttpStatus.OK).body("book is added successfully");
    }
    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<?> updateBook(@RequestBody Book book){
        Book bo = bookUtil.findByISBN(book.getIsbn());
        if(bo == null)
            return ResponseEntity.badRequest().body("There is no book has an ISBN equal to " + book.getIsbn());
        bookUtil.updateBook(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book is updated successfully");
    }
    @DeleteMapping(value = "/{isbn}", produces = "application/json")
    public ResponseEntity<?> deleteBook(@PathVariable("isbn") int isbn){
        Book book = bookUtil.findByISBN(isbn);
        if(book==null)
            return ResponseEntity.badRequest().body("There is no book has an ISBN equal to " + isbn);
        bookUtil.deleteBook(isbn);
        return ResponseEntity.status(HttpStatus.OK).body("book is deleted successfully");
    }
    @GetMapping(value = "isbn/{isbn}", produces = "application/json")
    public ResponseEntity<?> getBook(@PathVariable("isbn") int isbn){
        Book book = bookUtil.findByISBN(isbn);
        if(book==null)
            return ResponseEntity.badRequest().body("There is no book has an ISBN equal to " + isbn);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @GetMapping(value = "author/{author}", produces = "application/json")
    public ResponseEntity<?> searchBooks(@PathVariable("author") String author){
        Book book = bookUtil.findByAuthor(author);
        if(book == null)
            return ResponseEntity.badRequest().body("There is no book has an author equal to " + author);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @GetMapping("/health")
    public String CheckHealth(){
        return "\"success\": true";
    }
}
