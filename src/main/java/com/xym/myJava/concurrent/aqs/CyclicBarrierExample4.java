package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-03-21 9:43
 */
public class CyclicBarrierExample4 {
    /**
     * 当前屏障为3，所以需要3个线程
     */
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(3, () -> {
        System.out.println(Thread.currentThread().getName() + "到达屏障点...开始新一轮执行");
    });

    public static void main(String[] args) {
        new Thread(() -> test(), "ThreadA").start();
        new Thread(() -> {
            test();
        }, "ThreadB").start();
        new Thread(() -> {
            test();
        }, "ThreadC").start();
    }

    /**
     * 模拟多步骤做事，一步一步做,且多个线程要同步(线程1在做第一个步骤时，线程2、3也在做第一步)
     */
    private static void test() {
        System.out.println(Thread.currentThread().getName() + "--step1");
        try {
            CYCLIC_BARRIER.await();
            System.out.println(Thread.currentThread().getName() + "--step2");
            CYCLIC_BARRIER.await();
            System.out.println(Thread.currentThread().getName() + "--step3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
