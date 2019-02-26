package com.xym.myJava.thread.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁、非公平锁
 *
 * @author xym
 * @create 2019-02-25 14:35
 */
public class FairorNofairLock {
    public static void main(String[] args) {
        final Service service = new Service(false);//true为公平锁，false为非公平锁
        Runnable runnable = () -> {
            System.out.println("★线程" + Thread.currentThread().getName()
                    + "运行了");
            service.serviceMethod();
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }

    public static class Service {

        private ReentrantLock lock;

        /**
         * 参数表示是否公平
         *
         * @param isFair
         */
        public Service(boolean isFair) {
            super();
            this.lock = new ReentrantLock(isFair);
        }

        public void serviceMethod() {
            lock.lock();
            try {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + "获得锁定");
            } finally {
                lock.unlock();
            }
        }
    }
}
