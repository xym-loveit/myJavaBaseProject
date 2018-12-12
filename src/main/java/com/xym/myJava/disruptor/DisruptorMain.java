package com.xym.myJava.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 主函数：创建生产者，向ringbuffer中填充元素
 *
 * @author xym
 * @create 2018-12-12 9:52
 */
public class DisruptorMain {
    public static void main(String[] args) throws InterruptedException {
        //创建线程池：
        Executor executor = Executors.newCachedThreadPool();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //事件生产工厂：
        LongEventFactory longEventFactory = new LongEventFactory();
        //ringbuffer的大小：
        int bufferSize = 256;

        /**
         * Disruptor：Disruptor的入口，主要封装了环形队列RingBuffer、消费者集合ConsumerRepository的引用；主要提供了获取环形队列、
         * 添加消费者、生产者向RingBuffer中添加事件（可以理解为生产者生产数据）的操作；
         */


        /**
         * RingBuffer：Disruptor中队列具体的实现，底层封装了Object[]数组；在初始化时，会使用Event事件对数组进行填充，
         * 填充的大小就是bufferSize设置的值；此外，该对象内部还维护了Sequencer（序列生产器）具体的实现；
         *
         */

        /**
         * Sequencer：序列生产器，分别有MultiProducerSequencer（多生产者序列生产器） 和
         * SingleProducerSequencer（单生产者序列生产器）两个实现类。上面的例子中，
         * 使用的是SingleProducerSequencer；在Sequencer中，维护了消费者的Sequence（序列对象）和生产者自己的Sequence（序列对象）；
         * 以及维护了生产者与消费者序列冲突时候的等待策略WaitStrategy；
         *
         */

        /**
         * Sequence：序列对象，内部维护了一个long型的value，这个序列指向了RingBuffer中Object[]数组具体的角标。
         * 生产者和消费者各自维护自己的Sequence；但都是指向RingBuffer的Object[]数组；
         */


        /**
         * Wait Strategy：等待策略。当没有可消费的事件时，消费者根据特定的策略进行等待；当没有可生产的地方时，生产者根据特定的策略进行等待；
         */

        /**
         * Event：事件对象，就是我们Ringbuffer中存在的数据，在Disruptor中用Event来定义数据，并不存在Event类，它只是一个定义；
         */


        /**
         * EventProcessor：事件处理器，单独在一个线程内执行，判断消费者的序列和生产者序列关系，
         * 决定是否调用我们自定义的事件处理器，也就是是否可以进行消费；
         */

        /**
         * EventHandler：事件处理器，由用户自定义实现，也就是最终的事件消费者，需要实现EventHandler接口；
         */

        /**
         * Producer：事件生产者，也就是我们上面代码中最后那部分的for循环；
         */

        //实例化disruptor对象：初始化ringbuffer
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory, bufferSize, threadFactory, ProducerType.SINGLE, new BlockingWaitStrategy());
        //设置事件的执行者：(单消费者)
        disruptor.handleEventsWith(new LongEventHandler());
        //disruptor启动：
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //设置事件单生产者：
        for (int x = 0; x < 256; x++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            // 返回可用位置的元素
            LongEvent event = ringBuffer.get(sequence);
            System.out.println(event);
            // 设置该位置元素的值
            event.setValue(x);
            //发布事件
            ringBuffer.publish(sequence);
            Thread.sleep(10);
        }
    }
}
