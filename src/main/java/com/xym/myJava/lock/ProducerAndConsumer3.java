package com.xym.myJava.lock;

import java.util.LinkedList;

/**
 * 使用wait和notify的多个线程交互（生产者和消费者模型）
 * <p>
 * <p>
 * 此时如果有多个消费者和生产者呢？
 * <p>
 * 针对2出现的二种问题解决如下：
 * <p>
 * 采用while循环，在线程被唤醒获取monitor之后首先需要再次判断条件是否满足，并用notifyall替换notify唤醒对象的wait set里的所有线程
 *
 * @author xym
 * @create 2018-07-16 16:16
 */
public class ProducerAndConsumer3 {

    private final LinkedList<MyEvent> queue = new LinkedList<MyEvent>();

    public static final int MAX_EVENT = 10;

    private int max;

    public ProducerAndConsumer3(int max) {
        this.max = max;
    }

    public ProducerAndConsumer3() {
        this(MAX_EVENT);
    }

    public static void main(String[] args) {

        /**
         * 生产者线程正常生产无延迟
         */
        final ProducerAndConsumer3 queueEvent = new ProducerAndConsumer3();
        new Thread("producer1") {
            @Override
            public void run() {
                for (int i = 0; ; i++) {
                    queueEvent.offer(new ProducerAndConsumer3.MyEvent(i));
                   /* try {
                        TimeUnit.MILLISECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }.start();


        new Thread("producer2") {
            @Override
            public void run() {
                for (int i = 0; ; i++) {
                    queueEvent.offer(new ProducerAndConsumer3.MyEvent(i));
                   /* try {
                        TimeUnit.MILLISECONDS.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
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
                   /* try {
                        TimeUnit.MILLISECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }.start();

        new Thread("consumer2") {
            @Override
            public void run() {
                for (; ; ) {
                    queueEvent.take();
                    /*try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
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
            while (queue.size() >= max) {
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
            queue.notifyAll();
        }
    }

    /**
     * 获取
     *
     * @return
     */
    private MyEvent take() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    console("the queue is empty!");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            MyEvent myEvent = queue.removeFirst();
            queue.notifyAll();
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
