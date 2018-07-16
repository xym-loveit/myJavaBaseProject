package com.xym.myJava.lock;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-16 15:18
 */
public class MutexTest {

    private final static Object MUTEX = new Object();

    public void accessResource() {
        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final MutexTest mutex = new MutexTest();
        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    mutex.accessResource();
                }
            }.start();
        }
    }

}
