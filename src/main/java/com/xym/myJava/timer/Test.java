package com.xym.myJava.timer;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-19 0:03
 */
public class Test {

    /**
     * 总次数
     */
    public static final int TOTAL = 15;
    public static int COUNT = 0;
    public static int SWITCH_THRESHOLD = 3;

    public static Random random = new Random();

    public static void main(String[] args) {

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

        Job job = new Job("早上七点开始执行---job1", executorService);
        ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(job, 2, 1, TimeUnit.SECONDS);
        job.setFuture(scheduledFuture);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Job implements Runnable {
        private AtomicInteger count = new AtomicInteger(0);
        private final ScheduledExecutorService service;
        private final String name;
        private Future future;

        public Job(String name, ScheduledExecutorService service) {
            this.name = name;
            this.service = service;
        }


        public void setFuture(Future future) {
            this.future = future;
        }

        @Override
        public void run() {
            if (COUNT == TOTAL) {
                System.out.println("任务执行完毕！！");
                future.cancel(false);
                return;
            }

            //22点
            if (count.get() == SWITCH_THRESHOLD) {
                Optional.ofNullable(future).ifPresent((f) -> {
                    System.out.println("该换人了吧--------------->" + COUNT);
                    Boolean apply = f.cancel(true);
                    if (apply) {
                        Job job = new Job("早上七点开始执行---job" + ((COUNT % 2 == 0) ? "2" : "1"), service);
                        //7点开始
                        ScheduledFuture<?> f2 = service.scheduleWithFixedDelay(job, 3, 2, TimeUnit.SECONDS);
                        job.setFuture(f2);
                        return;
                    }

                });
            }

            //try {
            //    TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(Thread.currentThread() + "-->" + name + " work count " + count.incrementAndGet() + ",current time=" + formatter.format(now));
            COUNT++;
        }
    }
}
