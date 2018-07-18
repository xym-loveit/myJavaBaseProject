package com.xym.myJava.latch;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-18 17:53
 */
public class CountDownLatch extends Latch {

    /**
     * 任务都执行完毕后的回调
     */
    private Runnable runnable;

    public CountDownLatch(int limit, Runnable runnable) {
        super(limit);
        this.runnable = runnable;
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }

            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException {
        if (time <= 0) {
            throw new IllegalArgumentException("the time is invalid");
        }

        //将time转为nano
        long nanos = unit.toNanos(time);
        final long endNanos = (System.nanoTime() + nanos);
        synchronized (this) {
            while (limit > 0) {
                if (TimeUnit.NANOSECONDS.toMillis(nanos) <= 0) {
                    throw new WaitTimeoutException("the wait time over specify time");
                }

                //等待过程中有可能会被中断,需要重新计算nanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(nanos));
                nanos = (endNanos - System.nanoTime());
            }

            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived! ");
            }

            //使limit减一，并且通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    /**
     * 返回多少线程还未完成任务
     *
     * @return
     */
    @Override
    public int getUnarrived() {
        return limit;
    }
}
