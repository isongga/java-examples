package com.rj.demo.design.pattern.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemberContext2 {
    // 用户折扣策略接口
    private Map<String, MemberStrategy> strategys = new HashMap<>();

    public MemberContext2() {
        strategys.put("primary", new PrimaryMemberStrategy());
        strategys.put("intermediate", new IntermediateMemberStrategy());
        strategys.put("advance",new AdvanceMemberStrategy());
    }

    // 注入构造方法
    public MemberStrategy chooseStrategy(String type) throws Exception {
        MemberStrategy s = strategys.get(type);
        if(Objects.isNull(s)) {
            throw new Exception("unsupported strategy");
        }
        return s;
    }
}
