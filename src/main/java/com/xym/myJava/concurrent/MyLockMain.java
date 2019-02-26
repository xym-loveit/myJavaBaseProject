package com.xym.myJava.concurrent;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 10:17
 */
public class MyLockMain {

    private static int count = 0;

    static class MyThread implements Runnable {
        private MyLock lock;

        public MyThread(MyLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "--" + count);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
       /* MyLock myLock = new MyLock();
        Thread[] threadlocals = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadlocals[i] = new Thread(new MyThread(myLock), "th " + i);
            threadlocals[i].start();
        }*/
        System.out.println(MAX_COUNT);
    }
        static final int MAX_COUNT      = (1 << 16) - 1;
}
