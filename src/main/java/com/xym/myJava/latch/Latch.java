package com.xym.myJava.latch;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-18 17:47
 */
public abstract class Latch {
    /**
     * 用于控制多少个线程完成执行任务时才能打开阀门
     */
    protected int limit;

    //通过构造函数传入limit
    public Latch(int limit) {
        this.limit = limit;
    }

    /**
     * 该方法会使得线程一直等待，直到所有的线程都完成工作
     * 被阻塞的线程是允许被中断的
     */
    public abstract void await() throws InterruptedException;

    /**
     * 具备超时功能的等待
     *
     * @throws InterruptedException
     */
    public abstract void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException;

    /**
     * 当任务线程完成工作之后会调用该方法使得计数器减一
     */
    public abstract void countDown();

    /**
     * 获取当前还有多少个线程没有完成任务
     *
     * @return
     */
    public abstract int getUnarrived();
}
