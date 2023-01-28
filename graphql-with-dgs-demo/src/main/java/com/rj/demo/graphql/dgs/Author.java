package com.rj.demo.graphql.dgs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Author {
    private Integer id;

    private String firstName;

    private String lastName;

    private static List<Author> authors = Arrays.asList(
            new Author(1, "Joanne", "Rowling"),
            new Author(2, "Herman", "Melville"),
            new Author(3, "Anne", "Rice")
    );

    public static Author getById(Integer id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

}