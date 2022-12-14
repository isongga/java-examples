package com.rj.demo.design.pattern.strategy;

public interface MemberStrategy {
    //price：商品单价， n:商品个数
    double calcPrice(double price, int n);
}
