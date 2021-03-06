package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

// Demo88.java 的源码
class MyThread extends Thread{  
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+" is running");
    } 
}; 

public class Demo88 {
    public static void main(String[] args) {  
        Thread mythread=new MyThread("mythread");

        System.out.println(Thread.currentThread().getName()+" call mythread.run()");
        mythread.run();

        System.out.println(Thread.currentThread().getName()+" call mythread.start()");
        mythread.start();
    }  
}