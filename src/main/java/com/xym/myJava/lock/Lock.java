package com.xym.myJava.lock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 定义锁接口
 *
 * @author xym
 * @create 2018-07-16 18:12
 */
public interface Lock {

    /**
     * 该方法可被中断
     *
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 该方法可被中断，且支持超时功能
     *
     * @param mills
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 释放锁
     */
    void unlock();

    /**
     * 用于获取当前有哪些线程被阻塞
     *
     * @return
     */
    List<Thread> getBlockedThreads();
}
