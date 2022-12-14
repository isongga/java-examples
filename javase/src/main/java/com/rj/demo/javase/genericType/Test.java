package com.rj.demo.javase.genericType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List l = new ArrayList<Integer>();
        l = Collections.checkedList(l, Integer.class);
        l.add("hello");
        System.out.println(l);
    }
}
