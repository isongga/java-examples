package com.rj.demo.javase.hook;

public class TestHook {
    static {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> System.out.println("第1个钩子函数")));
        runtime.addShutdownHook(new Thread(() -> System.out.println("第2个钩子函数")));
    }

    public static void main(String[] args) {

    }
}
