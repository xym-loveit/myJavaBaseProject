package com.xym.myJava;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞生产者
 *
 * @author xym
 */
public class BlockProducer implements Runnable {

    private volatile boolean isRunning = true;
    /**
     * 缓冲区
     */
    private BlockingQueue<PCData> queue;
    /**
     * 总数 原子操作
     */
    private static AtomicInteger count = new AtomicInteger();
    /**
     * 随机睡眠
     */
    private static final int SLEEPTIME = 1000;

    public BlockProducer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        PCData data = null;
        System.out.println("start producer id=" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEPTIME));
                //构造任务数据
                data = new PCData(count.incrementAndGet());
                System.out.println(data + " is put into queue");
                //提交数据到缓冲区
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("failed to put data:" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
