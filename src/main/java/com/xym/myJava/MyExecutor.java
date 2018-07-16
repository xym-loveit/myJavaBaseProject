package com.xym.myJava;

import java.util.concurrent.Callable;

/**
 * @author xym
 */
public class MyExecutor {

    public <V> MyFuture<V> execute(final Callable<V> task) {
        final Object lock = new Object();
        final ExecuteThread<V> vExecuteThread = new ExecuteThread<>(task, lock);
        vExecuteThread.start();
        MyFuture<V> myFuture = new MyFuture<V>() {
            @Override
            public V get() throws Exception {
                synchronized (lock) {
                    while (!vExecuteThread.isDone()) {
                        System.out.println("获取结果等待中...");
                        lock.wait();
                    }
                }
                if (vExecuteThread.getException() != null) {
                    throw vExecuteThread.getException();
                }
                return vExecuteThread.getResult();
            }
        };
        return myFuture;
    }

    public static void main(String[] args) throws Exception {
        MyExecutor myExecutor = new MyExecutor();
        final   int a = 10;
        MyFuture<String> future = myExecutor.execute(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~run task");
               if(a==10){
                   throw new IllegalArgumentException("参数传递有误！");
               }
                return "result-100";
            }
        });
        String s = future.get();
        System.out.println(s);
    }
}
