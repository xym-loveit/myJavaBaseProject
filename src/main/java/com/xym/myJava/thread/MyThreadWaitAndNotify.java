package com.xym.myJava.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * wait/notify配合用来处理多线程协调处理的场景中
 * <p>
 * 当方法wait()被执行后，锁自动被释放，但执行完notify()方法后，锁不会自动释放。
 * 必须执行完notify()方法所在的synchronized代码块后才释放。
 *
 * @author xym
 * @create 2019-02-22 16:31
 */
public class MyThreadWaitAndNotify {
    public static void main(String[] args) {
        Object lock = new Object();

        ThreadA1 a = new ThreadA1(lock);
        a.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadB1 b = new ThreadB1(lock);
        b.start();
    }
}

class MyList {
    private static List<String> list = new ArrayList<String>();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }
}

class ThreadA1 extends Thread {

    private Object lock;

    public ThreadA1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                if (MyList.size() != 5) {
                    System.out.println("wait begin "
                            + System.currentTimeMillis());
                    lock.wait();//wait会释放当前锁，从运行状态退出，进入等待队列中，直到被唤醒
                    System.out.println("wait end  "
                            + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadB1 extends Thread {

    private Object lock;

    public ThreadB1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    MyList.add();
                    if (MyList.size() == 5) {
                        lock.notify();//观察notify并不会释放锁，所以for循环会一直执行完毕
                        System.out.println("已发出通知！");
                    }
                    System.out.println("添加了" + (i + 1) + "个元素!");
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
