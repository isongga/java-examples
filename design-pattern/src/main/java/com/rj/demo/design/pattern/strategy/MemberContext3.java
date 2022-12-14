package com.rj.demo.design.pattern.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MemberContext3 {
    // 用户折扣策略接口
    private Map<String, BiFunction<Double, Integer, Double>> strategys = new HashMap<>();

    MemberStrategyImpl memberStrategy = new MemberStrategyImpl();

    public MemberContext3() {
        strategys.put("primary", (arg1, arg2) -> memberStrategy.calcPrice1(arg1, arg2));
        strategys.put("intermediate", (arg1, arg2) -> memberStrategy.calcPrice2(arg1, arg2));
        strategys.put("advance",(arg1, arg2) -> memberStrategy.calcPrice3(arg1, arg2));
    }

    // 注入构造方法
    public Double doByStategy(String type, Double arg1, Integer arg2) {
        return strategys.get(type).apply(arg1, arg2);
    }
}
