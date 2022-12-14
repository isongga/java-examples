package com.rj.demo.design.pattern.strategy;

public class IntermediateMemberStrategy  implements MemberStrategy{
    @Override
    public double calcPrice(double price, int n) {
        return 0;
    }
}
