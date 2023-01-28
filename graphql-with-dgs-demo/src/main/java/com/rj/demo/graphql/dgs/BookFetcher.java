package com.rj.demo.graphql.dgs;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class BookFetcher {
    @Autowired
    BookByIdFetcher bookByIdFetcher;

    @DgsQuery
    public Book bookById(@InputArgument Integer id) {
        return bookByIdFetcher.bookById(id);
    }

    @DgsQuery
    public List<Book> allBooks(@InputArgument Integer id) {
        return Book.books;
    }
}
