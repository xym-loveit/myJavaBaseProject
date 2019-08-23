package com.xym.myJava.thread.cnblogs_skywang12345;

// Demo4.java的源码
public class Demo4 {

    public synchronized void synMethod() {
        int j = 0;
        for (int i = 0; i < 100000000; i++) {
            j++;
        }
        System.out.println(j);
    }

    public void synBlock() {
        synchronized( this ) {
            int j = 0;
            for (int i = 0; i < 100000000; i++) {
                j++;
            }
            System.out.println(j);
        }
    }

    public static void main(String[] args) {
        Demo4 demo = new Demo4();

        long start, diff;
        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synMethod();                                // 调用“synchronized方法”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synMethod() : "+ diff);
        
        start = System.currentTimeMillis();                // 获取当前时间(millis)
        demo.synBlock();                                // 调用“synchronized方法块”
        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
        System.out.println("synBlock()  : "+ diff);
    }
}