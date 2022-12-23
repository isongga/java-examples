package com.rj.demo.groovyunittest;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAllUser();
}
