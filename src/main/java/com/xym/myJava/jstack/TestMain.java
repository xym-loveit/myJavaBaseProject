package com.xym.myJava.jstack;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-08-_01 11:45
 */
public class TestMain {
    public static void main(String[] args) throws IOException {

       /* while (true) {
            System.out.println("1");
        }*/
        //t1();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(System.in);

        while(bufferedInputStream.read()!=-1){

        }
        t2();
        t3();




    }


    public static void t1() {
        Thread thread = new Thread(new Thread1(), "x1");
        thread.start();
    }


    static Object object = new Object();

    public static void t2() {
        new Thread("xxx") {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        object.wait();
                        System.out.println("--------------------------------被唤醒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void t3() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (object) {
            object.notifyAll();
            System.out.println("发起唤醒---------------");
        }
    }
}

class Thread1 implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println(1);
        }
    }
}
