package com.xym.myJava.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 10:16
 */
public class MyLock {
    private AtomicInteger status = new AtomicInteger(0);
    public void lock() {
        while(!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }
    public void unlock() {
        status.compareAndSet(1, 0);
    }
}
