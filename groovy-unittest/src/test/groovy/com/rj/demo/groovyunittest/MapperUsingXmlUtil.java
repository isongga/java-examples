package com.rj.demo.groovyunittest;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class MapperUsingXmlUtil {
    private static final String MAPPER_CONFIG_LOCATION = "mybatis-config.xml";

    public static <T> T getMapper(Class<T> type, DataSource dataSource) throws IOException {
        InputStream inputStream  = Resources.getResourceAsStream(MAPPER_CONFIG_LOCATION);
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", jdbcTransactionFactory, dataSource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().setEnvironment(environment);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return  sqlSession.getMapper(type);
    }
}
