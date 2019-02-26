package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.*;

/**
 * CyclicBarrier构造函数参数表示屏障拦截的线程数量，每个线程调用await方法告诉 CyclicBarrier 我已经到达了屏障，
 * 然后当前线程被阻塞。
 * <p>
 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活
 *
 * @author xym
 * @create 2019-02-26 11:37
 */
public class CyclicBarrierExample2 {
    // 请求的数量
    private static final int threadCount = 550;
    // 需要同步的线程数量，请求数量达到我们定义的 5 个的时候， await方法之后的方法才被执行
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(100);
            threadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }

    public static void test(int threadnum) throws InterruptedException, BrokenBarrierException {
        System.out.println("threadnum:" + threadnum + "is ready");
        try {
            //请求数量达到我们定义的 5 个的时候， await方法之后的方法才被执行
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("-----CyclicBarrierException------");
        }
        System.out.println("threadnum:" + threadnum + "is finish");
    }
}
