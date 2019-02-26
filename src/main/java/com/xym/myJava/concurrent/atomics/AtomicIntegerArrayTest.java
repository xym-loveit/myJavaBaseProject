package com.xym.myJava.concurrent.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组类型原子类
 *
 * @author xym
 * @create 2019-02-26 10:12
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int temvalue = 0;
        int[] nums = {1, 2, 3, 4, 5, 6};
        AtomicIntegerArray i = new AtomicIntegerArray(nums);
        for (int j = 0; j < nums.length; j++) {
            System.out.println(i.get(j));
        }
        //获取指定位置值，并设置为新值
        temvalue = i.getAndSet(0, 2);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
        //获取指定位置的值，并递增1
        temvalue = i.getAndIncrement(0);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
        //获取指定位置的值，并增加预期的值
        temvalue = i.getAndAdd(0, 5);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
    }
}
