package com.rj.demo.graphql.controller;

import com.rj.demo.graphql.entity.Author;
import com.rj.demo.graphql.entity.Book;
import com.rj.demo.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @QueryMapping
    public Book bookById(@Argument Integer id) {
        return bookService.getBookId(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }
}
