package com.rj.demo.groovyunittest;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnotherUserDao {
    @Select("SELECT * FROM user")
    List<User> getAllUser();
}
