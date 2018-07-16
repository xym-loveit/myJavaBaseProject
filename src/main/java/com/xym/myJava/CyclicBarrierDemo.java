package com.xym.myJava;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏，是CountDownLatch的增强，可以在多个线程wait达到之后重新计算，如此循坏工作
 *
 * @author xym
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final int N = 10;
        Thread[] threads = new Thread[N];
        boolean flag = false;
        //设置屏障点
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + " 报道！");
            threads[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            threads[i].start();
            if (i == 5) {
                //模拟中断，一个发生中断之后，其他等待是徒劳，所以其他线程会直接抛出BrokenBarrierException
                threads[i].interrupt();
            }
        }
    }

    public static class Soldier implements Runnable {

        private String soldier;
        private CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵到齐
                cyclicBarrier.await();
                doWork();
                /**
                 * 等待所有士兵完成工作
                 */


                /**
                 * 开始下一次计数
                 */
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() throws InterruptedException {
            Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            System.out.println(soldier + ":任务完成！");
        }
    }

    /**
     *
     */
    public static class BarrierRun implements Runnable {

        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        /**
         * 计数器达到指标后会执行当前方法
         */
        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：士兵 " + N + " 个任务完成！");
            } else {
                System.out.println("司令：士兵 " + N + " 个集合完毕!");
                flag = true;
            }
        }
    }
}
