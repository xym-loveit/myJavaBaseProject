package com.xym.myJava.thread;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-22 16:14
 */
public class MyThread02Run {
    public static void main(String[] args) {
        MyThread02[] myThread02s = new MyThread02[100];
        for (int i = 0; i < 100; i++) {
            myThread02s[i] = new MyThread02();
        }

        for (int i = 0; i < 100; i++) {
            myThread02s[i].start();
        }
    }
}
