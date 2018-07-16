package com.xym.myJava;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列实现的消费者
 *
 * @author xym
 */
public class BlockConsumer implements Runnable {

    private BlockingQueue<PCData> queue;

    private static final int SLEEPTIME = 1000;

    public BlockConsumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        System.out.println("start consumer id=" + Thread.currentThread().getId());
        Random random = new Random();
        try {
            while (true) {
                //获取任务数据
                PCData take = queue.take();
                if (null != take) {
                    //计算平方
                    int ret = take.getIntData() * take.getIntData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}", take.getIntData(), take.getIntData(), ret));
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(SLEEPTIME));
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
