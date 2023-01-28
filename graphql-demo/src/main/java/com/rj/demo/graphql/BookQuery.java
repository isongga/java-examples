//package com.rj.demo.graphql;
//
//import com.rj.demo.graphql.dao.BookMapper;
//import com.rj.demo.graphql.entity.Book;
//import graphql.kickstart.tools.GraphQLQueryResolver;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class BookQuery implements GraphQLQueryResolver {
//
//    @Autowired
//    private final BookMapper bookMapper;
//
//    private Book bookById(UUID id) {
//        return bookMapper.selectByPrimaryKey(1);
//    }
//}
