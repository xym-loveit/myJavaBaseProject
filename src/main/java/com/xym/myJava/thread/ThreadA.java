package com.xym.myJava.thread;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-22 11:22
 */
public class ThreadA extends Thread {
    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }
}
