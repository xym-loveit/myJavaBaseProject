package com.xym.myJava.thread;

/**
 * 虽然加了锁，但是2个对象，所以锁是2个锁，异步执行互不影响
 *
 * @author xym
 * @create 2019-02-22 11:25
 */
public class Run {
    public static void main(String[] args) {
        HasSelfPrivateNum privateNum1 = new HasSelfPrivateNum();
        HasSelfPrivateNum privateNum2 = new HasSelfPrivateNum();
        ThreadA athread = new ThreadA(privateNum1);
        athread.start();
        ThreadB bthread = new ThreadB(privateNum2);
        bthread.start();
    }
}
