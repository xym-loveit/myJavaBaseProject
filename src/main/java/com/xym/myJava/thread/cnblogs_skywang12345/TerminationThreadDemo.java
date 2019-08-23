package com.xym.myJava.thread.cnblogs_skywang12345;

/**
 * interrupted：是静态方法，通过Thread类直接调用
 * * 可以看到调用的是isInterrupted并且传递参数是true，表示要清除中断标记
 * * public static boolean interrupted() {
 * * return currentThread().isInterrupted(true);
 * * }
 * <p>
 * isInterrupted：是用thread对象调用（成员方法），并且传递给isInterrupted参数是false，表示不清除中断标记
 * public boolean isInterrupted() {
 * return isInterrupted(false);
 * }
 * <p>
 * 不管是interrupted、还是interrupted最终调用的都是isInterrupted本地方法，只是传参不一样
 * private native boolean isInterrupted(boolean ClearInterrupted);
 *
 * @author xym
 * @create 2019-08-23 22:29
 */
public class TerminationThreadDemo extends Thread {


    public TerminationThreadDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        //myInterrupt();
        myInterrupt2();
        //myInterrupt3();
        //myInterrupt4();
    }


    public void myInterrupt4() {
        int i = 0;
        while (true) {
            System.out.println("loop i=" + (++i) + Thread.interrupted());
        }
    }

    public void myInterrupt3() {
        int i = 0;
        while (true) {
            System.out.println("loop i=" + (++i) + Thread.interrupted());
            boolean interrupted = Thread.currentThread().isInterrupted();
            if (interrupted) {
                System.out.println("exit ");
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " over--" + Thread.currentThread().isInterrupted());
    }

    public /*synchronized*/ void myInterrupt2() {
        int i = 0;
        while (true) {
            try {
                Thread.sleep(100);
                System.out.println("loop i=" + (++i) + Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
                System.err.println("loop i=" + (++i) + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("loop i>>>>>>>>>111" + (++i) + Thread.currentThread().isInterrupted());
        System.out.println("loop i>>>>>>>>>222" + (++i) + Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().getName() + " over");
    }


    public /*synchronized*/ void myInterrupt() {
        try {
            int i = 0;
            while (true) {
                Thread.sleep(100);
                System.out.println("loop i=" + (++i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " over");
    }


    public static void main(String[] args) throws InterruptedException {
        TerminationThreadDemo t1 = new TerminationThreadDemo("t1");
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
        Thread.sleep(2000);
        System.out.println("main over--"+t1.isInterrupted());
    }
}
