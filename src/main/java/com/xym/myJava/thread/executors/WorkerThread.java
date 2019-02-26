package com.xym.myJava.thread.executors;

import java.util.Date;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 16:29
 */
public class WorkerThread implements Runnable {

    private String command;

    public WorkerThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    @Override
    public String toString() {
        return this.command;
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
