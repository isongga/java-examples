package com.rj.demo.groovyunittest;

import com.zaxxer.hikari.HikariDataSource
import groovy.sql.Sql

import javax.sql.DataSource

class SpecUtils {
    static DataSource memoryDataSource() {
        return new HikariDataSource().with{ dataSource ->
            dataSource.driverClassName = 'org.h2.Driver'
            dataSource.jdbcUrl = 'jdbc:h2:mem:'
            dataSource.username = 'sa'
            dataSource.password = ''
            dataSource
        }

//        return new DataSource().with { dataSource ->
//            dataSource.driverClassName = 'org.h2.Driver'
//            dataSource.url = 'jdbc:h2:mem:'
//            dataSource.username = 'sa'
//            dataSource.password = ''
//            dataSource
//        }
    }

    static void createUserTable(DataSource dataSource) {
        assert dataSource
//        new Sql(dataSource).execute("CREATE TABLE User(id INT PRIMARY KEY, name VARCHAR(255), ip VARCHAR(50))")
        new Sql(dataSource).execute("drop table if exists user; create table `user` (`id` int(11) not null auto_increment,  `userid` int(11) not null,`username` varchar(32) not null,PRIMARY KEY (`id`))")
    }

    static void dropUserTable(DataSource dataSource) {
        assert dataSource
//        new Sql(dataSource).execute("CREATE TABLE User(id INT PRIMARY KEY, name VARCHAR(255), ip VARCHAR(50))")
        new Sql(dataSource).execute("drop table if exists user;")
    }

}
