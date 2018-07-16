package com.xym.myJava;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park面对中断不会抛出InterruptedException异常，只会默默返回，但可以从
 * Thread.interrupted方法获得中断标记
 *
 * @author xym
 */
public class LockSupportIntDemo {

    private static Object u = new Object();
    private static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    private static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.interrupted()) {
                    System.out.println(getName() + " 被中断了！");
                }
            }
            System.out.println(getName() + " 执行结束!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        /**
         * 触发t1中断
         *
         */
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}
