package com.xym.myJava.oop;

/**
 * 调用最匹配的那个方法
 *
 * @author xym
 */
public class Child2 extends Base2 {

    public long sum(long a, long b) {
        System.out.println("Child2_long_long");
        return a + b;
    }

    public long sum2(long a, long b) {
        System.out.println("Child2_long_long");
        return a + b;
    }

    @Override
    public long sum2(int a, long b) {
        System.out.println("Child2_long_long");
        return a + b;
    }

    public static void main(String[] args) {
        Child2 child2 = new Child2();
        child2.sum2(2, 3);
    }

}
