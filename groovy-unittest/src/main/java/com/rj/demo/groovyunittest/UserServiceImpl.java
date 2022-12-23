package com.rj.demo.groovyunittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(long id) {
        List<User> all = userDao.getAllUser();
        for (User user : all) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> queryUser() {
        return null;
    }

    @Override
    public boolean save(User user) {
        return false;
    }
}
