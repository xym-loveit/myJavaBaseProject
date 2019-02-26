package com.xym.myJava.thread.waitNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition实现顺序执行
 * <p>
 * 通过多个condition来实现，a通知-->b通知--->c通知---a
 *
 * @author xym
 * @create 2019-02-25 14:10
 */
public class ConditionSeqExec {

    volatile private static int nextPrintWho = 1;
    private static ReentrantLock lock = new ReentrantLock();
    final private static Condition conditionA = lock.newCondition();
    final private static Condition conditionB = lock.newCondition();
    final private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 1) {
                    conditionA.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadA " + (i + 1));
                }
                nextPrintWho = 2;
                //通知conditionB实例的线程运行
                conditionB.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        );

        Thread threadB = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 2) {
                    conditionB.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadB " + (i + 1));
                }
                nextPrintWho = 3;
                //通知conditionC实例的线程运行
                conditionC.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 3) {
                    conditionC.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("ThreadC " + (i + 1));
                }
                nextPrintWho = 1;
                //通知conditionA实例的线程运行
                conditionA.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread[] threadsA = new Thread[5];
        Thread[] threadsB = new Thread[5];
        Thread[] threadsC = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threadsA[i] = new Thread(threadA);
            threadsB[i] = new Thread(threadB);
            threadsC[i] = new Thread(threadC);

            threadsA[i].start();
            threadsB[i].start();
            threadsC[i].start();
        }
    }
}
