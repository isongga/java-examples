package com.rj.demo.javase.innerclass;

public class OuterClass {

    String s;

    static class StaticInnerClass {
        public void show(){
            System.out.println("show");
        }

        public static void staticShow(){
            System.out.println("static show");
        }
    }

    private InnerClass innerClass;

    public InnerClass getInnerClass() {
        return new InnerClass();
    }

     public class InnerClass {
         public void show(){
             System.out.println("InnerClass show");
         }
     }
}
