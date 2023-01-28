package com.rj.demo.graphql.dgs;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.rj.demo.graphql.dgs.dao.AuthorMapper;
import com.rj.demo.graphql.dgs.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class BookByIdFetcher {
    @Autowired
    BookMapper bookMapper;

    @Autowired
    AuthorMapper authorMapper;

    @DgsData(parentType = "Query", field = "bookById")
    public Book bookById(@InputArgument Integer id) {
        return bookMapper.selectByPrimaryKey(1);
    }

    @DgsData(parentType = "Book", field = "author")
    public Author author(DgsDataFetchingEnvironment dfe) {

        Book book = dfe.getSource();
        return authorMapper.selectByPrimaryKey(book.getAuthorId());
    }
}
