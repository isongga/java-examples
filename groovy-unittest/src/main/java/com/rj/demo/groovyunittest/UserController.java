package com.rj.demo.groovyunittest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public Long register(@RequestBody User user) {
        try {
            List<User> list = userService.queryUser();
            if (!CollectionUtils.isEmpty(list)) {
                log.error("注册失败，用户名已存在");
                return -1L;
            }
            if (!userService.save(user)) {
                log.error("注册失败,数据库保存失败");
                return -2L;
            }
            log.info("注册成功");
            return user.getId();
        } catch (Exception e) {
            log.error("注册异常", e);
            if (e instanceof ServiceException) {
                throw e;
            }
            return -3L;
        }
    }
}