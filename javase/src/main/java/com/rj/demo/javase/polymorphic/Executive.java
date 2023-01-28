package com.rj.demo.javase.polymorphic;

import java.math.BigDecimal;

public class Executive extends Employee {
    @Override
    public void raise(Number number) {
        System.out.println("Executive got raise!");
    }

    public void raise(BigDecimal amount) {
        System.out.println("Executive got outlandish raise!");
    }

    public static void giveRaise(Employee employee) {
        employee.raise(new BigDecimal(1000.0));
    }

    public static void main(String[] args) {
        giveRaise(new Employee());
        giveRaise(new Executive());
    }
}
