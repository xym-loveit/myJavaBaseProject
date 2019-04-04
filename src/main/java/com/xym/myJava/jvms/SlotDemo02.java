package com.xym.myJava.jvms;

/**
 * 在同一个作用域内进行，不会进行gc
 *
 * @author xym
 * @create 2019-04-01 15:53
 */
public class SlotDemo02 {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        //虽然已经超出作用域范围，但在此后没有任何对局部变量表的读写操作
        //placeholder原本占用的Solt还没有被其他变量复用
        System.gc();
    }
}
