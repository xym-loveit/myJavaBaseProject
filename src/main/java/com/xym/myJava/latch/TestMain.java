package com.xym.myJava.latch;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-18 22:43
 */
public class TestMain {
    public static void main(String[] args) {
        //test01();
        test02();
    }

    private static void test02() {
        final Latch latch = new CountDownLatch(4, () -> System.out.println(Thread.currentThread().getName() + "终于都到齐了---继续出发下一个目的地！"));
        ProgrammerTravel p1 = new ProgrammerTravel(latch, "张三", "公交车");
        ProgrammerTravel p2 = new ProgrammerTravel(latch, "李四", "宝马");
        ProgrammerTravel p3 = new ProgrammerTravel(latch, "王五", "奥迪");
        ProgrammerTravel p4 = new ProgrammerTravel(latch, "赵6", "雅迪");

        p1.start();
        p2.start();
        p3.start();
        p4.start();

        try {
            //main线程会进入阻塞，直到四个程序员全部到达目的地
            latch.await(TimeUnit.SECONDS, 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (WaitTimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("==all of programmer arrived==");
    }


    private static void test01() {
        final Latch latch = new CountDownLatch(4, () -> System.out.println("终于都到齐了---继续出发下一个目的地！"));
        ProgrammerTravel p1 = new ProgrammerTravel(latch, "张三", "公交车");
        ProgrammerTravel p2 = new ProgrammerTravel(latch, "李四", "宝马");
        ProgrammerTravel p3 = new ProgrammerTravel(latch, "王五", "奥迪");
        ProgrammerTravel p4 = new ProgrammerTravel(latch, "赵6", "雅迪");

        p1.start();
        p2.start();
        p3.start();
        p4.start();


       /* TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前还有多少个没有到达=" + latch.getUnarrived());
                if (0 == latch.getUnarrived()) {
                    this.cancel();
                }
            }
        };

       final Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
*/
        try {
            //main线程会进入阻塞，直到四个程序员全部到达目的地
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("==all of programmer arrived==");
    }
}
