package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

/**
 * 这种终止线程的做法很聪明：
 * <p>
 * 1、包括正常情况下的线程终止 while (!isInterrupted()) {
 * <p>
 * 2、包括阻塞情况下的线程终止 catch (InterruptedException e) {
 *
 * @author xym
 * @create 2019-08-23 23:31
 */
public class GeneralStopThread extends Thread {
    @Override
    public void run() {
        try {
            // 1. isInterrupted()保证，只要中断标记为true就终止线程。
            while (!isInterrupted()) {
                // 执行任务...
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // 2. InterruptedException异常保证，当InterruptedException异常产生时，线程被终止。
        }
    }
}
