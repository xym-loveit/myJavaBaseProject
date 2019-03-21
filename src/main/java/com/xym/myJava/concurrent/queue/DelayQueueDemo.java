package com.xym.myJava.concurrent.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 基于优先级队列实现的无界，阻塞延迟队列,put不会阻塞，但take会
 *
 * @author xym
 * @create 2019-03-18 23:48
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Delay> delays = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Delay delay = new Delay(random.nextInt(1200), "task--" + i);
            delays.offer(delay);
        }
        Delay delay = null;
        while (true) {
            if (delays.size() == 0) {
                System.out.println("---");
                break;
                //一旦没有元素，则task会阻塞
            } else if ((delay = delays.take()) != null) {
                System.out.println(delay);
            }
        }
    }


    static class Delay implements Delayed {
        //延迟时间
        private final long delayTime;
        //到期时间
        private final long expire;
        //任务名称
        private String name;

        public Delay(long delayTime, String name) {
            this.delayTime = delayTime;
            this.expire = System.currentTimeMillis() + delayTime;
            this.name = name;
        }

        /**
         * 剩余时间=到期时间-当前时间
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * 优先级队列里面的优先级规则
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "Delay{" +
                    "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
