package com.xym.myJava.thread.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实例
 * <p>
 * 1、读读共享
 * <p>
 * 2、读写互斥
 * <p>
 * 3、写写互斥
 *
 * @author xym
 * @create 2019-02-25 14:46
 */
public class ReadAndWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        ThreadA a = new ThreadA(service,true);
        a.setName("A");
        a.start();

        Thread.sleep(1000);

        ThreadB b = new ThreadB(service,false);
        b.setName("B");
        b.start();
    }

    static public class ThreadA extends Thread {

        private Service service;
        private boolean isRead;

        public ThreadA(Service service, boolean isRead) {
            this.service = service;
            this.isRead = isRead;
        }

        @Override
        public void run() {
            if (isRead) {
                service.read();
            } else {
                service.write();
            }
        }
    }

    static public class ThreadB extends Thread {

        private Service service;
        private boolean isRead;

        public ThreadB(Service service, boolean isRead) {
            this.service = service;
            this.isRead = isRead;
        }

        @Override
        public void run() {
            if (isRead) {
                service.read();
            } else {
                service.write();
            }
        }
    }


    static public class Service {

        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        /**
         *
         */
        public void read() {
            lock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得读锁" + System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }


        public void write() {
            lock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得写锁" + System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }

    }
}
