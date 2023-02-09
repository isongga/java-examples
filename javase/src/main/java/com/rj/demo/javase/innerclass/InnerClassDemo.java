package com.rj.demo.javase.innerclass;

public class InnerClassDemo {
    public void test() {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.getInnerClass();
        OuterClass.StaticInnerClass.staticShow();
    }
}
