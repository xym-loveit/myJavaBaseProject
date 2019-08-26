package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

import java.util.concurrent.CountDownLatch;

// Demo1.java
// 仓库
class Depot {
    private int capacity;    // 仓库的容量
    private int size;        // 仓库的实际数量

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    //如果生产量过大，只能分多批生产，因为容量有限（e.g. 容量为100，而你要生产500），多批生产伴随生产线程等待
    //如果生产的量比较小，一次就生产完了
    //生产速度过快，消费者速度太慢，也有可能等待
    public synchronized void produce(int val) {
        try {
            // left 表示“想要生产的数量”(有可能生产量太多，需多此生产)
            int left = val;
            while (left > 0) {
                // 库存已满时，等待“消费者”消费产品。
                //为了程序逻辑严谨这里最好使用循环判断
                while (size >= capacity) {
                    wait();
                }
                //capacity=99 98
                //size=95+10=105
                //4
                // 获取“实际生产的数量”(即库存中新增的数量)
                // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                // 否则“实际增量”=“想要生产的数量”
                int inc = (size + left) > capacity ? (capacity - size) : left;
                size += inc;
                left -= inc;//0/6
                System.out.printf("%s produce(%3d) --> 未生产数量=%3d, 实际生产数量=%3d, 实际库存=%3d\n",
                        Thread.currentThread().getName(), val, left, inc, size);
                // 通知“消费者”可以消费了。
                notifyAll();
            }
        } catch (InterruptedException e) {
        }
    }

    /**
     * 一次性消费量太多比如当前
     *
     * @param val
     */
    public synchronized void consume(int val) {
        try {
            // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
            int left = val;
            while (left > 0) {
                // 库存为0时，等待“生产者”生产产品。
                //为了程序逻辑严谨这里最好使用循环判断
                while (size <= 0) {
                    wait();
                }
                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                //size=10 left=5
                int dec = (size < left) ? size : left;
                size -= dec;
                left -= dec;//结果为0表示一次性拿走了（够拿）
                //
                System.out.printf("%s consume(%3d) <-- 未消费数量=%3d, 当前实际消费=%3d, 仓库剩余=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                notifyAll();//表示有坑位了
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return "容量:" + capacity + ",实际库存:" + size;
    }
}

// 生产者
class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        this.depot = depot;
    }

    // 消费产品：新建一个线程向仓库中生产产品。
    public void produce(final int val) {
        new Thread() {
            @Override
            public void run() {
                depot.produce(val);
                Demo10.latch.countDown();
            }
        }.start();
    }
}

// 消费者
class Customer {
    private Depot depot;

    public Customer(Depot depot) {
        this.depot = depot;
    }

    // 消费产品：新建一个线程从仓库中消费产品。
    public void consume(final int val) {
        new Thread() {
            @Override
            public void run() {
                depot.consume(val);
                Demo10.latch.countDown();
            }
        }.start();
    }
}

public class Demo10 {

    public static CountDownLatch latch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {

        Depot mDepot = new Depot(100);
        System.out.println(mDepot);
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);

        latch.await();
        System.out.println(mDepot);
    }
}