package com.xym.myJava;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 演示使用阻塞队列创建的消费者和生产者
 *
 * @author xym
 */
public class BlockProducerAndConsumer {
    public static void main(String[] args) throws InterruptedException {
        //创建缓冲区
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(10);
        BlockProducer blockProducer1 = new BlockProducer(queue);
        BlockProducer blockProducer2 = new BlockProducer(queue);
        BlockProducer blockProducer3 = new BlockProducer(queue);
        //消费者
        BlockConsumer blockConsumer1 = new BlockConsumer(queue);
        BlockConsumer blockConsumer2 = new BlockConsumer(queue);
        BlockConsumer blockConsumer3 = new BlockConsumer(queue);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(blockProducer1);
        executorService.execute(blockProducer2);
        executorService.execute(blockProducer3);
        //运行消费者
        executorService.execute(blockConsumer1);
        executorService.execute(blockConsumer2);
        executorService.execute(blockConsumer3);
        Thread.sleep(1000 * 10);
        //停止生产者
        blockProducer1.stop();
        blockProducer2.stop();
        blockProducer3.stop();
        Thread.sleep(3000);

        executorService.shutdown();
    }
}
