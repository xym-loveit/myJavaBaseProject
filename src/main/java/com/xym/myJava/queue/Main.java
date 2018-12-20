package com.xym.myJava.queue;

import java.util.concurrent.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-14 16:30
 */
public class Main {
    static int len = 5;

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(len);
        for (int s = 0; s < len; s++) {
            queue.add(s + "");
        }
        //队列满时抛出异常
        //queue.add("123");
        //队列满时返回false
        //boolean offer = queue.offer("123");
        //System.out.println(offer);
        //try {
        //    //队列满时线程阻塞
        //    queue.put("123");
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //try {
        //    //队列满时，线程先阻塞一段时间，超时则直接返回
        //    boolean offer = queue.offer("123", 10, TimeUnit.SECONDS);
        //    System.out.println(offer);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        //清空队列
        queue.clear();
        System.out.println(queue);
        /**
         * NoSuchElementException(队列空时，抛出异常)
         */
        //System.out.println(queue.remove());
        /**
         * 队列空时，返回null
         */
        //System.out.println(queue.poll());
        //try {
        //    //队列空时，线程一直阻塞；
        //    System.out.println(queue.take());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        /**
         * 队列空时，线程被阻塞一段时间，超时则直接返回
         */

        //try {
        //    System.out.println(queue.poll(10, TimeUnit.SECONDS));
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        /**
         * LinkedBlockingQueue可以为有界和无界队列
         */
        LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<String>();
        for (int i = 0; i < 100; i++) {
            queue1.add(i + "");
        }
        //System.out.println(queue1);
        //LinkedBlockingQueue<String> queue2 = new LinkedBlockingQueue<String>(10);
        //for (int i = 0; i < 100; i++) {
        //    queue2.add(i + "");
        //}
        //System.out.println(queue2);

        /**
         * 无界阻塞队列
         */
        PriorityBlockingQueue<String> queue2 = new PriorityBlockingQueue<>(10, (String i1, String i2) -> Integer.parseInt(i2) - Integer.parseInt(i1));
        //for (int i = 0; i < 10; i++) {
        //    queue2.add(i + "");
        //}
        //for (int i = 0; i < 10; i++) {
        //    System.out.println(queue2.poll());
        //}
        //try {
        //    System.out.println(queue2.take());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        /**
         * 链表结构的无界阻塞队列
         */
        //LinkedTransferQueue queue3 = new LinkedTransferQueue();
        //for (int i = 0; i < 100; i++) {
        //    queue3.add(i + "");
        //}
        //System.out.println(queue3);
        //queue3.clear();
        //try {
        //    System.out.println(queue3.take());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}


        /**
         * ：一个由链表结构组成的双向阻塞队列
         */
        //LinkedBlockingDeque<String> strings = new LinkedBlockingDeque<>(10);
        //for (int i = 0; i < 11; i++) {
        //    try {
        //        strings.putFirst(i + "");
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}
    }
}
