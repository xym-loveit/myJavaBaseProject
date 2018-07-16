package com.xym.myJava.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-16 22:27
 */
public class BooleanLockTest {

    private final BooleanLock booleanLock = new BooleanLock();

    public static void main(String[] args) {
        BooleanLockTest booleanLockTest = new BooleanLockTest();
        new Thread("t1") {
            @Override
            public void run() {
                booleanLockTest.syncMethod();
            }
        }.start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread("t2") {
            @Override
            public void run() {
                booleanLockTest.syncMethodTimeoutable();
            }
        }.start();

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //test02();

        //test01();
    }

    /**
     * 线程获取锁超时时，抛出超时异常
     */
    private void syncMethodTimeoutable() {
        try {
            booleanLock.lock(1000);

            System.out.println(Thread.currentThread() + "get the lock!");
            int i = ThreadLocalRandom.current().nextInt(10);
            TimeUnit.SECONDS.sleep(i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            booleanLock.unlock();
        }
    }

    /**
     * 模拟被中断
     */
    private static void test02() {

        BooleanLockTest booleanLockTest = new BooleanLockTest();
        new Thread("t1") {
            @Override
            public void run() {
                booleanLockTest.syncMethod();
            }
        }.start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                booleanLockTest.syncMethod();
            }
        };

        t2.start();

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //t1线程此时还未执行完，t2现处于blocked状态，等待t1
        t2.interrupt();
    }


    private static void test01() {
        final BooleanLockTest booleanLockTest = new BooleanLockTest();

        for (int i = 0; i < 10; i++) {
            new Thread("t" + i) {
                @Override
                public void run() {
                    //多线程下实现了获取锁和释放锁功能，和一个正常的synchronized功能相当
                    booleanLockTest.syncMethod();
                }
            }.start();
        }
    }

    /**
     * 使用try catch块保证每次锁都能被正确释放
     */
    public void syncMethod() {
        try {
            //加锁
            booleanLock.lock();

            int i = ThreadLocalRandom.current().nextInt(5);
            System.out.println("sleep " + i);
            System.out.println("currentThread   " + Thread.currentThread() + "get the lock!");
            TimeUnit.SECONDS.sleep(i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁
            booleanLock.unlock();
            System.out.println(booleanLock.getBlockedThreads());
        }
    }
}
