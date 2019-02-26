package com.xym.myJava.thread.joins;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 10:16
 */
public class ThreadA extends Thread {

    private ThreadB threadB;

    public ThreadA(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        synchronized (threadB) {
            threadB.start();
            try {
                //当前线程释放锁，然后
                threadB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10000; i++) {
                System.out.println("a do sth....");
            }
        }
    }
}
