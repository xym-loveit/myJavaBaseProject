package com.xym.myJava.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-22 10:11
 */
public class SharedVariableThread extends Thread {
    //private int count = 200;//volatile修饰无法解决原子性（仅仅解决可见性）

    private AtomicInteger count = new AtomicInteger(200);

    public static void main(String[] args) {
        SharedVariableThread sharedVariableThread = new SharedVariableThread();
        sharedVariableThread.test();
    }

    private void test() {
        //for (int i = 0; i < count; i++) {
        for (int i = 0; i < count.get(); i++) {
            new Thread(this, "Thread " + i).start();
        }
    }

    @Override
    public void run() {
        super.run();
        //count--;
        count.decrementAndGet();
        System.out.println("由 " + SharedVariableThread.currentThread().getName() + " 计算，count=" + count);
    }
}
