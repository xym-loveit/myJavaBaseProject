package com.xym.myJava.oop;

/**
 * @author xym
 */
public class Child1 extends Base1 {

    public static String b = "Child1 b";
    public String a = "Child1 a";

    public static void staticTest(){
        System.out.println("Child1 staticTest..."+b);
    }

    public static void main(String[] args) {
        Child1 child1 = new Child1();
        Base1 base1 = child1;
        System.out.println(base1.a);
        System.out.println(base1.b);
        base1.staticTest();
        System.out.println(child1.a);
        System.out.println(child1.b);
        child1.staticTest();
    }
}
