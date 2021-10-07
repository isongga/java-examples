package com.rj.demo.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

public class ApplicationListenerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println(Thread.currentThread().getName() + " received spring event!" + event);
            }
        });
        System.out.println(Thread.currentThread().getName()  + " before refresh");
        context.refresh();
        System.out.println(Thread.currentThread().getName()  + " after refresh and before close");
        context.close();
        System.out.println(Thread.currentThread().getName()  + " after close");
    }
}
