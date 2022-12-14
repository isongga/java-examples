package com.rj.demo.javase.flame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlameGraphDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        while (true) {
            executorService.submit(FlameGraphDemo::handle1);
            executorService.submit(FlameGraphDemo::handle2);
            executorService.submit(FlameGraphDemo::handle3);
            executorService.submit(FlameGraphDemo::handle4);
        }


    }

    private static void handle4() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void handle2() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void handle3() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void handle1() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
