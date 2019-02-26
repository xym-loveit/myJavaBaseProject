package com.xym.myJava.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-22 16:13
 */
public class MyThread02 extends Thread {

    //只能保证可见性，不能保证原子性
    //volatile public static int count;

    /**
     * 只能保证部分原子性
     */
    public static AtomicInteger count = new AtomicInteger(0);


    /**
     * Atomic系列在此方法中只能保证局部原子性，不能保证整个方法的原子性
     * <p>
     * 想要保障 set 和get2个方法在一起的原子性就得使用“synchronized”
     * count.set
     * count.get
     */
    private synchronized static void addCount() {
        for (int i = 0; i < 100; i++) {
            count.set(i);
        }
        System.out.println("count=" + count.get());

    }

    @Override
    public void run() {
        addCount();
    }
}
