package com.xym.myJava.thread.waitNotify;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 11:36
 */
public class UseSingleConditionWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
        Thread.sleep(3000);
        service.signal();
    }

    static public class MyService {
        private Lock lock = new ReentrantLock();
        public Condition condition = lock.newCondition();


        /**
         * 等待方法
         */
        public void await() {
            lock.lock();//必须在condition.await()方法调用之前调用lock.lock()代码获得同步监视器
            try {
                System.out.println("await的时间为=" + System.currentTimeMillis());
                condition.await();
                System.out.println("这是condition.await()方法之后的语句，condition.signal()方法之后我才被执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 唤醒方法
         */
        public void signal() {
            lock.lock();
            try {
                System.out.println("signal时间为" + System.currentTimeMillis());
                condition.signal();//signal相当于notify，也不会释放锁，直到把当前的try执行完毕，释放锁之后，await后面的语句才能执行
                Thread.sleep(3000);
                System.out.println("这是condition.signal()方法之后的语句");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static public class ThreadA extends Thread {
        private MyService service;

        public ThreadA(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.await();
        }
    }
}

