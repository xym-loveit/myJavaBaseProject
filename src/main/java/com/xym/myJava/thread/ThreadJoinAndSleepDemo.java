package com.xym.myJava.thread;

/**
 * join和sleep的区别
 * <p>
 * sleep不会释放锁所以Thread02会一直执行完毕
 * <p>
 * join会释放锁，释放的是调用join方法的当前线程占用的锁
 *
 * @author xym
 * @create 2019-02-22 18:01
 */
public class ThreadJoinAndSleepDemo {

    public static void main(String[] args) throws InterruptedException {
       /* Object lock = new Object();
        Thread03 thread03 = new Thread03(lock);
        thread03.start();
        //thread03.join();
        Thread.sleep(100);
        new Thread02(lock).start();*/


        /*如果2个线程使用的是2个不同的锁，则异步执行互不影响*/
        Object lock01 = new Object();
        Object lock02 = new Object();
        Thread03 thread03 = new Thread03(lock01);
        thread03.start();
        //thread03.join();
        //Thread.sleep(100);
        //new Thread02(lock02).start();
    }
}

class Thread02 extends Thread {

    private Object lock;

    public Thread02(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 500; i++) {
                try {
                    //观察sleep会不会释放锁
                    Thread.sleep(20);
                    System.out.println(Thread.currentThread().getName() + " i=" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Thread03 extends Thread {
    private Object lock;

    public Thread03(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            /*锁被当前线程占用，需要等当前线程执行完毕后才能执行*/
            Thread02 thread02 = new Thread02(lock);
            thread02.start();
            try {
                thread02.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 500; i++) {
                try {
                    Thread.sleep(20);
                    if (i == 100) {

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName() + "--i=" + i);
            }
        }
    }
}
