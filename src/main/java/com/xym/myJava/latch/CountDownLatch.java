package com.xym.myJava.latch;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-18 17:53
 */
public class CountDownLatch extends Latch {

    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                this.wait();
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
