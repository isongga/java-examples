package com.rj.demo.javase.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    CyclicBarrier cb = new CyclicBarrier(3, ()->{});
}
