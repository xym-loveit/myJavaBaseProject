package com.xym.myJava.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 通过jdk延迟队列实现
 *
 * @author xym
 * @create 2018-09-04 14:00
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("00000001");
        list.add("00000002");
        list.add("00000003");
        list.add("00000004");
        list.add("00000005");

        DelayQueue<OrderDelay> queue = new DelayQueue<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            //延迟三秒取出
            queue.put(new OrderDelay(list.get(i),
                    //3秒转为纳秒
                    TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS)));
            try {
                queue.take().print();
                System.out.println("After " +
                        (System.currentTimeMillis() - start) + " MilliSeconds");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
