package com.rj.demo.graphql.dgs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Data
public class Book {
    private Integer id;

    private String name;

    private Integer pageCount;

    private Integer authorId;

    public static List<Book> books = Arrays.asList(
            new Book(1, "Harry Potter and the Philosopher's Stone", 223, 1),
            new Book(2, "Moby Dick", 635, 2),
            new Book(3, "Interview with the vampire", 371, 3)
    );

    public static Book getById(Integer id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }
}