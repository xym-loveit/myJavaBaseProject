package com.xym.myJava.thread.joins;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 10:21
 */
public class ThreadC extends Thread {
    private ThreadB threadB;

    public ThreadC(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.myService();
    }
}
