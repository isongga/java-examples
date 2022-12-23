package com.rj.demo.groovyunittest

import spock.lang.Specification

class IDNumberUtilsTest extends Specification {
    def "测试"() {
        given: //一般是第一个代码块，我们在此准备需要验证的数据
        def list = new ArrayList()

        when: //在when 中执行我们的逻辑，
        list.add(1)
        list.add(2)
        list.add(3)
        def a = 1 + 1
        then: //在then中验证我们的逻辑. when 和 then 是必须配合使用的，when用来执行操作，then用来验证操作
        a == 2
        list.size() == 3
        and: "没啥用， 用来分隔，一般写点注释性语句，或者加几个条件。"
        expect: //相当于 when 和 then 的结合，独立出现，可以在里面执行一些语句，如果语句返回值是boolean，则会被当作断言进行判断。
        2 == Math.min(2, 3)
        l == Math.min(m, n);
        where://测试多组数据
        l | m | n
        1 | 1 | 3
        3 | 3 | 5
        4 | 4 | 6

    }
}
