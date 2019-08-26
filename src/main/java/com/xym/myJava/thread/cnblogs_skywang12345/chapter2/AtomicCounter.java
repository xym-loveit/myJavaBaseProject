package com.xym.myJava.thread.cnblogs_skywang12345.chapter2;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-24 19:33
 */
public class AtomicCounter implements Counter {

    private AtomicLong counter = new AtomicLong(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public long getCounter() {
        return counter.get();
    }
}
