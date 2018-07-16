package com.xym.myJava.lock;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeoutException;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-16 18:16
 */
public class BooleanLock implements Lock {

    /**
     * 代表当前拥有锁的线程
     */
    private Thread currentThread;

    /**
     * false表示当前锁没有被任何线程获得或已经释放，true代表该锁已经被某个线程获得，
     * 该线程就是currentThread
     */
    private boolean locked = false;

    /**
     * 用来存储哪些线程在获取当前实例时进入了阻塞状态
     */
    private final List<Thread> blockedList = new CopyOnWriteArrayList<>();

    /**
     * @throws InterruptedException
     */
    @Override
    public void lock() throws InterruptedException {
        //lock方法使用同步代码块方式进行同步
        synchronized (this) {
            /**
             * 一旦锁被获取则将当前线程加入wait列表中
             */
            while (locked) {
                final Thread tempThread = Thread.currentThread();
                try {
                    if (!blockedList.contains(tempThread)) {
                        blockedList.add(tempThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    /*如果当前线程在wait被中断，则从blockedList中将其删除，避免内存泄露*/
                    blockedList.remove(tempThread);
                    /*继续抛出中断异常*/
                    throw e;
                }
            }
            /**
             * 如果没被锁定，如果当前线程是从wait set中被唤醒，则需要从阻塞队列中将自己删除
             */
            blockedList.remove(Thread.currentThread());
            //开关指定为true
            this.locked = true;
            //记录获取锁的线程
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            /**
             * 如果mills不合法，则默认调用lock方法
             */
            if (mills <= 0) {
                this.lock();
            } else {
                /**
                 *如果remainingMills小于等于0表示当前线程被其他线程唤醒或者在指定的wait时间到了之后还没有获得锁，这种情况直接抛出超时异常
                 */
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during " + mills + " ms");
                    }
                    if (!blockedList.contains(Thread.currentThread())) {
                        blockedList.add(Thread.currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }

                blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            /**
             * 哪个线程对其加锁，只能由该线程来解锁
             */
            if (currentThread == Thread.currentThread()) {
                this.locked = false;
                System.out.println(Thread.currentThread().getName() + " release the lock!");
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return blockedList;
    }
}
