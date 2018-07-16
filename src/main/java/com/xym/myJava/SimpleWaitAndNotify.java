package com.xym.myJava;

/**
 * Object.wait和Thread.sleep方法都可以让线程等待若干时间，除了wait可以被唤醒外，另外一个主要区别就是wait方法会释放目标对象的锁，而
 * Thread.sleep不会释放任何资源
 *
 * @author xym
 */
public class SimpleWaitAndNotify {

    static final Object OBJECT = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (OBJECT) {
                System.out.println(System.currentTimeMillis() + ":T1 start！");
                System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                try {
                    /**
                     * 等待并释放object对象锁
                     */
                    OBJECT.wait();//wait后如果要重新执行，必须再次获取object锁

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end！");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (OBJECT) {
                System.out.println(System.currentTimeMillis() + ":T2 start! notify one thread");
                /**
                 * 还未释放锁
                 */
                OBJECT.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end！");

                try {
                    //特意睡眠2秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
    }
}
