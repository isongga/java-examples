package com.rj.demo.groovyunittest

import be.janbols.spock.extension.dbunit.DbUnit
import spock.lang.Specification

class UserDaoDB2Spec extends Specification{
    def datasource

    @DbUnit(datasourceProvider = {datasource})

    def content = {
        User(userId:1001, userName: "xiaoliang")
    }

    def setup() {
        datasource = SpecUtils.memoryDataSource()
        SpecUtils.createUserTable(datasource)
    }

    def cleanup() {
        SpecUtils.dropUserTable(datasource);
    }


    def "dao层测试"() {
        given: "获取mapper"
//        def userDao = MapperUsingJavaUtil.getMapper(AnotherUserDao.class, datasource)
        def userDao = MapperUsingXmlUtil.getMapper(UserDao.class, datasource)
        when: "执行"
        def result = userDao.getAllUser()
        println result
        then: "验证数据"
        result.size() == 1
        def re = result.get(0)
        with(re) {
            re.id == 1
            re.userId == 1001
            re.userName =="xiaoliang"
        }
    }
}

//1. 通过mybatis的sqlsession启动session 连接。版本问题？ 貌似是spock-dbutil与groovy4不兼容。
//2. jdbcdriver的问题，连本地mysql不可以
//3. org.dbunit.dataset.NoSuchTableException: User. 可能是config文件配置不对。
//3. 要熟悉这三个东西：dbutil,spock-dbutil, mybatis的session factory
