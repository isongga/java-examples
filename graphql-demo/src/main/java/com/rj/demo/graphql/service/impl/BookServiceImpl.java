package com.rj.demo.graphql.service.impl;

import com.rj.demo.graphql.dao.BookMapper;
import com.rj.demo.graphql.entity.Book;
import com.rj.demo.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book getBookId(Integer bookId) {
        return  bookMapper.selectByPrimaryKey(bookId);
    }
}
