package com.waa.lab3.controller;

import com.waa.lab3.model.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


class BookControllerTest {

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
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("")
                .then()
                .statusCode(200)
                .equals(books);
    }

    @Test
    void addBook() {
        given()
                .contentType(ContentType.JSON)
                .body(book1)
                .when()
                .post("")
                .then()
                .statusCode(200);
    }

    @Test
    void updateBook() {
        given()
                .contentType(ContentType.JSON)
                .body(book1)
                .when()
                .put("")
                .then()
                .statusCode(200);
    }

    @Test
    void deleteBook() {
      given()
              .contentType(ContentType.JSON)
              .body(book1)
              .when()
                .then()
                .statusCode(200);
      given()
              .when()
              .get("isbn/1")
              .then()
              .body("isbn",equalTo(1))
              .body("title",equalTo("Book1"))
              .body("price",equalTo(10.00f))
              .body("author",equalTo("Book1Author"));
      given()
              .when()
              .delete("");
    }

    @Test
    void getBookByIsbn() {
        given()
                .contentType(ContentType.JSON)
                .body(book1)
                .when()
                .then()
                .statusCode(200);
        given()
                .when()
                .get("isbn/1")
                .then()
                .body("isbn",equalTo(1))
                .body("title",equalTo("Book1"))
                .body("price",equalTo(10.00f))
                .body("author",equalTo("Book1Author"));
    }

    @Test
    void searchBooks() {
        given()
                .contentType(ContentType.JSON)
                .body(book1)
                .when()
                .then()
                .statusCode(200);
        given()
                .when()
                .get("author/Book1Author")
                .then()
                .body("isbn",equalTo(1))
                .body("title",equalTo("Book1"))
                .body("price",equalTo(10.00f))
                .body("author",equalTo("Book1Author"));
    }
}