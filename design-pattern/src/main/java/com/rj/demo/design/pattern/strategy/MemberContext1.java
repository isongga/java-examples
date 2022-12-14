package com.rj.demo.design.pattern.strategy;

public class MemberContext1 {
    // 用户折扣策略接口
    private PrimaryMemberStrategy primaryMemberStrategy = new PrimaryMemberStrategy();
    private AdvanceMemberStrategy advanceMemberStrategy = new AdvanceMemberStrategy();
    private IntermediateMemberStrategy intermediateMemberStrategy = new IntermediateMemberStrategy();

    // 注入构造方法
    public MemberStrategy chooseStrategy(int mode) {
        MemberStrategy strategy;
        switch (mode) {
            case 1:
                strategy = primaryMemberStrategy;
                break;
            case 2:
                strategy = intermediateMemberStrategy;
                break;
            case 3:
                strategy = advanceMemberStrategy;
                break;
            default:
                strategy = primaryMemberStrategy;
                break;

        }
        return  strategy;
    }
}
