package com.xym.myJava;

/**
 * 高优先级的总是会先于低优先级的执行完
 *
 * @author xym
 */
public class PriorityDemo {


    /**
     * 高优先级线程
     */
    public static class HighPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("HighPriority is complete!" + System.currentTimeMillis());
                        break;
                    }
                }
            }
        }
    }

    /**
     * 低优先级线程
     */
    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("LowPriority is complete!" + System.currentTimeMillis());
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HighPriority highPriority = new HighPriority();
        LowPriority lowPriority = new LowPriority();
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.start();
        lowPriority.start();
    }
}
