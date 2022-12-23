package com.rj.demo.groovyunittest;

import javax.management.Query;
import java.util.List;

public interface IUserService {
    User getUserById(long id);
    List<User> queryUser();

    boolean save(User user);
}
