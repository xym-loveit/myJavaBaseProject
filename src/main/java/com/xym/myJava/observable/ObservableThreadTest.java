package com.xym.myJava.observable;

import java.util.concurrent.TimeUnit;

/**
 * 具备观察模式的运行线程
 *
 * @author xym
 * @create 2018-07-18 10:47
 */
public class ObservableThreadTest {

    public static void main(String[] args) {
        //test01();

        /**
         * 重写空finish回调
         */
        TaskLifecycle<String> taskLifecycle = new TaskLifecycle.EmptyLifecycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("the result is " + result);
            }
        };

        Observable observableThread = new ObservableThread<>(new Task<String>() {
            @Override
            public String call() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return String.valueOf(10 + 20);
            }
        }, taskLifecycle);

        observableThread.start();

    }

    private static void test01() {
        /**
         * 可以像调用thread一样使用ObservableThread
         */
        new ObservableThread<String>(new Task<Void>() {
            @Override
            public Void call() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finished done");
                return null;
            }
        }).start();
    }
}
