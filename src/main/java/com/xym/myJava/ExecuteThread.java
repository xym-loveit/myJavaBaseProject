package com.xym.myJava;

import java.util.concurrent.Callable;

/**
 * @author xym
 */
public class ExecuteThread<V> extends Thread {
    private V result;
    private Exception exception;
    private boolean done;
    private Callable<V> task;
    private Object lock;

    public ExecuteThread(Callable<V> task, Object lock) {
        this.task = task;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            result = task.call();
        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
        } finally {
            synchronized (lock) {
                done = true;
                System.out.println("唤醒等待主线程...");
                lock.notifyAll();
            }
        }
    }

    public V getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isDone() {
        return done;
    }
}
