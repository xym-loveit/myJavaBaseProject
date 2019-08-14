package com.xym.myJava.base;

import java.io.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-05 21:35
 */
public class OuterClass implements Serializable {

    int anInt = 100;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public static int bint = 1000;

    private synchronized void say() throws InterruptedException {
        wait();//synchronized
        new Thread(new Runnable() {

            @Override
            public void run() {
                a = 200;
                System.out.println(a);
            }
        }).start();
        //System.out.println("hehe");
        //Inner inner = new Inner();
        //inner.hehe();
        System.out.println(super.getClass().getName());
        System.out.println(super.getClass().getSuperclass().getName());
    }

    private synchronized void notifyy() {
        System.out.println("----------------即将唤醒start---------");
        notify();
        System.out.println("---------即将唤醒end------------");
    }


    static class Inner {
        private void hehe() {
            //System.out.println("xixi--------->" + anInt);
            System.out.println("xixi--------->" + bint);
        }
    }

    int a = 100;

    //@Override
    //protected void finalize() throws Throwable {
    //    super.finalize();
    //}

    public static void main(String[] args) throws InterruptedException {

        //OuterClass outerClass = new OuterClass();
        //outerClass.setAnInt(9999);
        //try {
        //    //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d:/outerClass.txt"));
        //    //out.writeObject(outerClass);
        //    ObjectInputStream in = new ObjectInputStream(new FileInputStream("d:/outerClass.txt"));
        //    OuterClass outerClass2 = (OuterClass) in.readObject();
        //    System.out.println(outerClass2.getAnInt());
        //} catch (IOException | ClassNotFoundException e) {
        //    e.printStackTrace();
        //}
        //Thread thread = Thread.currentThread();
        //OuterClass outerClass = new OuterClass();
        //
        //new Thread(() -> {
        //    try {
        //        Thread.sleep(4000);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    outerClass.notifyy();
        //}).start();
        //outerClass.say();
        //System.out.println("-----------------");
        //Inner inner = OuterClass.new Inner();
        //Inner i = new Inner();
        //new Inner().hehe();


        //String abc = "a,ccnjx,dfgfg,bvvv";
        //System.out.println(abc.split(",").length);
        //System.out.println(Arrays.toString(abc.split(",")));
        //String[] split = abc.split(",");
        //Arrays.sort(split);
        //System.out.println(Arrays.toString(split));
        //Arrays.sort(split, Comparator.reverseOrder());
        //System.out.println(Arrays.toString(split));
        //String s = "a" + "b" + "c" + "d";
        //System.out.println(s);

        //List<String> list = Files.readAllLines(Paths.get("C:\\Users\\xym\\Desktop\\信用算力.sql"));
        //for (String s : list) {
        //    System.out.println(s);
        //}

        //NumberFormatException
        //非检查型异常
        //Integer.parseInt("awe");
        //ArithmeticException
        //System.out.println(1 / 0);
        //String s = "";
        //System.out.println(s.equals("hello"));
        //int[] ar = new int[]{1,2,3,4,5};
        //System.out.println(ar[5]);
        //Object o = new Object();
        //Date ar1 = (Date)o ;
        //System.out.println(ar1);
        //Thread thread = new MyThread();
        //thread.start();
        //System.out.println(Thread.currentThread().getName());
        //new Thread(new MyRunnable()).start();
        //Runnable runnable = () -> System.out.println(Thread.currentThread().getId());
        //new Thread(runnable).start();
        //MyClass myClass = new MyClass();
        //new Thread() {
        //    @Override
        //    public void run() {
        //        myClass.test2();
        //    }
        //}.start();

        //new Thread() {
        //    @Override
        //    public void run() {
        //        myClass.test1();
        //    }
        //}.start();

    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId());
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId());
        }
    }


    static class MyClass {
        public synchronized void test1() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test1----");
        }

        public synchronized void test2() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2----");
        }
    }
}
