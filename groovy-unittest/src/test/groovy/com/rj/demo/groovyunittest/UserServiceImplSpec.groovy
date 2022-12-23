package com.rj.demo.groovyunittest

import spock.lang.Specification

class UserServiceImplSpec extends Specification {

    def userService = new UserServiceImpl()

    def "GetUserById"() {
        given: ""
        User u1 = new User(userId: 1, userName: "xiaoming")
        User u2 = new User(userId: 2, userName: "xiaohong")

        and: "dao"
        def userDao = Mock(UserDao)
        userService.userDao = userDao
        userDao.getAllUser() >> [u1, u2]

        when: "方法调用"
        def resp = userService.getUserById(2)

        then: ""
        with(resp) {
            userId == 2
            userName == 'xiaohong'
        }
    }
}
