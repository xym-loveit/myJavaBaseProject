package com.xym.myJava.thread;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-22 11:24
 */
public class ThreadB extends Thread {

    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("b");
    }
}
