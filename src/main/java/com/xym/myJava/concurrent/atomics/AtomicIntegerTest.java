package com.xym.myJava.concurrent.atomics;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-26 10:03
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        int tempValue = 0;
        AtomicInteger atomicInteger = new AtomicInteger();
        tempValue = atomicInteger.getAndSet(3);//获取并赋值
        System.out.println("tempValue=" + tempValue + ",v2=" + atomicInteger);
        tempValue = atomicInteger.getAndIncrement();//获取并递增
        System.out.println("tempValue=" + tempValue + ",v2=" + atomicInteger);
        tempValue = atomicInteger.getAndAdd(5);//获取并新增
        System.out.println("tempValue=" + tempValue + ",v2=" + atomicInteger);
    }
}
