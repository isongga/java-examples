package com.rj.demo.spring.ioc;

import com.rj.demo.spring.ioc.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        User user = (User)factory.getBean("user");
        System.out.println(user);
    }
}
