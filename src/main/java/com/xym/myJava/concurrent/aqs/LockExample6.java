package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该实例体现了lock、condition的经典使用
 * <p>
 * <p>
 * <p>
 * 输出顺序为：
 * <p>
 * 1、wait signal 线程1调用wait等待且释放锁
 * 2、get lock 线程2获取lock
 * 3、send signal 线程2发出唤醒信号
 * 4、get signal 线程1被唤醒重新继续执行
 * <p>
 * 可以看到，整个协调通信的过程是靠线程所在的节点在AQS的等待队列和condition的等待队列中来回移动实现的。
 * condition作为一个条件类很好的维护了一个等待信号的队列，
 * 并在signal 或者 signalAll方法被调用后，将等待的线程节点重新放回AQS的等待队列中，从而实现唤醒线程的操作。
 * <p>
 * <p>
 * 所以在condition上调用wait是将线程放入到condition信号队列中，
 * 而signal/signalAll是将信号队列中等待的线程Node转移至AQS阻塞队列，而在条件满足时出队并执行
 *
 * @author xym
 * @create 2019-03-08 9:38
 */
public class LockExample6 {
    public static void main(String[] args) {
        // 构建ReentrantLock实例
        ReentrantLock reentrantLock = new ReentrantLock();
        // 从reentrantLock实例里获取condition实例
        Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
            // 线程1调用了lock方法，这时线程1就会加入到了AQS的等待队里面去
            reentrantLock.lock();
            // 1 等待信号
            System.out.println("wait signal");
            try {
                // 调用await方法后，线程1就会从AQS队列里移除，这里其实就已经释放了锁，然后线程1会马上进入到condition队列里面去，等待一个信号
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get signal");  // 4 得到信号
            // 线程1释放锁，整个过程执行完毕
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            // 由于线程1中调用了await释放了锁的关系，所以线程2就会被唤醒获取到锁，加入到AQS等待队列中
            reentrantLock.lock();
            System.out.println("get lock");  // 2 获取锁
            try {
                // 睡眠3秒
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 调用signalAll发送信号的方法，此时condition等待队列里线程1所在的节点元素就会被取出，
            // 然后重新放到AQS等待队列里（注意此时线程1还没有被唤醒）
            condition.signalAll();
            System.out.println("send signal");   // 3 发送信号
            // 线程2释放锁，这时候AQS队列中只剩下线程1，然后AQS会按照从头到尾的顺序唤醒线程，于是线程1开始执行
            reentrantLock.unlock();
        }).start();

    }
}
