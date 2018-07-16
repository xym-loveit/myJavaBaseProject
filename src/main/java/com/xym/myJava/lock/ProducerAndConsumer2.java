package com.xym.myJava.lock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 使用wait和notify的单线程交互（生产者和消费者模型）
 * <p>
 * <p>
 * 此时如果有多个消费者和生产者呢？
 * <p>
 * 会有二种问题：
 * 1、queue为空时，却执行了removeFirst方法，此时会抛出异常
 * <p>
 * 2、queue满的时候，照样执行了addLase方法
 *
 * @author xym
 * @create 2018-07-16 16:16
 */
public class ProducerAndConsumer2 {

    private final LinkedList<MyEvent> queue = new LinkedList<MyEvent>();

    public static final int MAX_EVENT = 10;

    private int max;

    public ProducerAndConsumer2(int max) {
        this.max = max;
    }

    public ProducerAndConsumer2() {
        this(MAX_EVENT);
    }

    public static void main(String[] args) {

        /**
         * 生产者线程正常生产无延迟
         */
        final ProducerAndConsumer2 queueEvent = new ProducerAndConsumer2();
        new Thread("producer1") {
            @Override
            public void run() {
                for (int i = 0; ; i++) {
                    queueEvent.offer(new ProducerAndConsumer2.MyEvent(i));
                    try {
                        TimeUnit.MILLISECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        new Thread("producer2") {
            @Override
            public void run() {
                for (int i = 0; ; i++) {
                    queueEvent.offer(new ProducerAndConsumer2.MyEvent(i));
                    try {
                        TimeUnit.MILLISECONDS.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        /**
         * 消费者线程模拟一定的延迟
         */
        new Thread("consumer1") {
            @Override
            public void run() {
                for (; ; ) {
                    queueEvent.take();
                    try {
                        TimeUnit.MILLISECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread("consumer2") {
            @Override
            public void run() {
                for (; ; ) {
                    queueEvent.take();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    /**
     * 添加
     *
     * @param event
     */
    private void offer(MyEvent event) {
        synchronized (queue) {
            if (queue.size() > max) {
                console("the queue isfull!");
                console("current event " + event);
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is submitted!");
            /*没有达到上限则加入到队列末尾，且通知take线程*/
            queue.addLast(event);
            queue.notify();
        }
    }

    /**
     * 获取
     *
     * @return
     */
    private MyEvent take() {
        synchronized (queue) {
            if (queue.isEmpty()) {
                try {
                    console("the queue is empty!");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            MyEvent myEvent = queue.removeFirst();
            queue.notify();
            console("the event " + myEvent + " is handled");
            return myEvent;
        }
    }

    private void console(String message) {
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), message);
    }

    private static class MyEvent {

        int anInt;

        public MyEvent(int anInt) {
            this.anInt = anInt;
        }

        @Override
        public String toString() {
            return "MyEvent{" +
                    "anInt=" + anInt +
                    '}';
        }
    }
}
