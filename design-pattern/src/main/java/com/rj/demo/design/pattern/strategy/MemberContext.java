package com.rj.demo.design.pattern.strategy;

public class MemberContext {
    // 用户折扣策略接口
    private MemberStrategy memberStrategy;

    // 注入构造方法
    public MemberContext(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    // 计算价格
    public double qoutePrice(double goodsPrice, int n){
        // 通过接口变量调用对应的具体策略
        return memberStrategy.calcPrice(goodsPrice, n);
    }
}
