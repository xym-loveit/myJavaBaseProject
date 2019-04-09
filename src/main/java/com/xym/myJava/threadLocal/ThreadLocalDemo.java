package com.xym.myJava.threadLocal;

import java.util.concurrent.CountDownLatch;

/**
 * 1、一个thread有且仅有一个ThreadLocalMap对象
 * <p>
 * 2、一个Entry对象的key弱引用指向一个ThreadLocal对象
 * <p>
 * 3、一个ThreadLocalMap对象存储多个Entry对象
 * <p>
 * 4、一个ThreadLocal对象可以被多个线程所共享，共享的数据存储在每个线程的ThreadLocalMap里面
 * <p>
 * 5、ThreadLocalMap里面有Entry[] table,用来存放以ThreadLocal为key与Value组成的Entry
 * <p>
 * 6、threadLocalMap是default访问修饰（同包可见），无法查看，若要查看请在debug模式
 *
 * @author xym
 * @create 2019-04-07 15:43
 */
public class ThreadLocalDemo {
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "one");
    private static ThreadLocal<String> threadLoca2 = ThreadLocal.withInitial(() -> "two");
    private static ThreadLocal<String> threadLoca3 = ThreadLocal.withInitial(() -> "three");


    private static final int THREAD_NUM = 3;

    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(() -> {
                threadLocal.set(threadLocal.get() + "--1");
                threadLocal.set(threadLoca2.get() + "--2");
                threadLocal.set(threadLoca3.get() + "--3");
                countDownLatch.countDown();
            }, "MyThread--" + i);
            threads[i] = thread;
            thread.start();
        }

        countDownLatch.await();
        System.out.println("全部线程执行完毕!");

        for (Thread thread : threads) {
            System.out.println(thread);
        }
    }
}
