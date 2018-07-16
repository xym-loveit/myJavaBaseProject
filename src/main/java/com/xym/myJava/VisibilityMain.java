package com.xym.myJava;

/**
 * 线程可见性
 * <p>
 * volatile不能代替锁，也无法保证一些复合操作的原子性
 *
 * @author xym
 */
public class VisibilityMain {
    static volatile int i = 0;

    public static class PlusTask implements Runnable {

        @Override
        public void run() {
            for (int i1 = 0; i1 < 10000; i1++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i1 = 0; i1 < 10; i1++) {
            threads[i1] = new Thread(new PlusTask());
            threads[i1].start();
        }

        for (int i1 = 0; i1 < 10; i1++) {
            threads[i1].join();
        }

        System.out.println(i);

    }
}
