package com.xym.myJava.jvms;

/**
 * 在同一个作用域内进行，不会进行gc
 *
 * @author xym
 * @create 2019-04-01 15:53
 */
public class SlotDemo {
    public static void main(String[] args) {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        //在同一个作用域内进行，不会进行gc
        System.gc();
    }
}
