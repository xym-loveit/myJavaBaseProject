package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

// JoinTest.java的源码
public class JoinTest {

    /**
     * join的话想要理解清楚去看源代码
     * <p>
     * isAlive 注意是检查【调用join】方法的线程是否存活，一般为子线程
     * <p>
     * wait：注意阻塞的是当前正在执行的线程，一般为父线程
     * if (millis == 0) {
     * <p>
     * while (isAlive()) {
     * wait(0);
     * }
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”

            t1.start();                     // 启动“线程t1”
            t1.join();                        // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
            System.out.printf("%s finish\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.printf("%s start\n", this.getName());

            // 延时操作
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.printf("%s finish\n", this.getName());
        }
    }
}