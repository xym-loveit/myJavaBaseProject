package com.xym.myJava.latch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 程序员出游类
 *
 * @author xym
 * @create 2018-07-18 18:00
 */
public class ProgrammerTravel extends Thread {

    /**
     * 门栓
     */
    private final Latch latch;

    /**
     * 程序员
     */
    private final String programmer;

    /**
     * 交通工具
     */
    private final String transportaion;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportaion = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation[" + transportaion + "]");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by " + transportaion);
        //完成任务时，计数器减一
        latch.countDown();
    }
}
