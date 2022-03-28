package com.waa.lab3.controller;

import com.waa.lab3.model.Book;
import com.waa.lab3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> getAllBooks(){
        List<Book> books = bookService.getBook();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.OK).body("book is added successfully");
    }
    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Book bo = bookService.findByISBN(book.getIsbn());
        if(bo == null)
            return ResponseEntity.badRequest().body("There is no book has an ISBN equal to " + book.getIsbn());
        bookService.updateBook(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book is updated successfully");
    }
    @DeleteMapping(value = "/{isbn}", produces = "application/json")
    public ResponseEntity<?> deleteBook(@Valid @PathVariable("isbn") int isbn, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Book book = bookService.findByISBN(isbn);
        if(book==null)
            return ResponseEntity.badRequest().body("There is no book has an ISBN equal to " + isbn);
        bookService.deleteBook(isbn);
        return ResponseEntity.status(HttpStatus.OK).body("book is deleted successfully");
    }
    @GetMapping(value = "isbn/{isbn}", produces = "application/json")
    public ResponseEntity<?> getBook(@Valid @PathVariable("isbn") int isbn, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Book book = bookService.findByISBN(isbn);
        if(book==null)
            return ResponseEntity.badRequest().body("There is no book has an ISBN equal to " + isbn);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @GetMapping(value = "author/{author}", produces = "application/json")
    public ResponseEntity<?> searchBooks(@Valid @PathVariable("author") String author, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Book book = bookService.findByAuthor(author);
        if(book == null)
            return ResponseEntity.badRequest().body("There is no book has an author equal to " + author);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @GetMapping("/health")
    public String CheckHealth(){
        return "\"success\": true";
    }
}
