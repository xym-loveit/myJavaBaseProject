package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对于CyclicBarrier，重点是多个线程，在任意一个线程没有完成，所有的线程都必须等待
 *
 * @author xym
 * @create 2019-02-26 11:44
 */
public class CyclicBarrierExample3 {
    // 请求的数量
    private static final int threadCount = 550;
    // 需要同步的线程数量, 请求数量达到我们定义的 5 个的时候， await方法之后的方法才被执行
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        System.out.println("------当线程数达到之后，优先执行------");
    });

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
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

    private static void test(int threadNum) throws BrokenBarrierException, InterruptedException {
        System.out.println("threadnum:" + threadNum + "is ready");
        cyclicBarrier.await(); //请求数量达到我们定义的 5 个的时候， await方法之后的方法才被执行
        System.out.println("threadnum:" + threadNum + "is finish");
    }
}
