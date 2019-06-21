package com.xym.myJava.jdk8.lambda;

import java.util.function.Consumer;

/**
 * lambda表达式执行一次处理
 *
 * @author xym
 * @create 2019-06-21 11:31
 */
public class ExceptionDemo {
    public static void doInOrder(Runnable first, Runnable second, Consumer<Throwable> handler) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    //操作1
                    first.run();
                    //操作2
                    second.run();
                } catch (Throwable t) {
                    //通过函数式接口Consumer<Throwable>来处理lambda表达式执行过程中出现的异常
                    handler.accept(t);
                }
            }
        };
        t.start();
    }

    public static void main(String[] args) {
        doInOrder(
                () -> System.out.println(args[0]),
                () -> System.out.println("Goodbye"),
                (e) -> {
                    System.out.println(e);
                    e.printStackTrace();
                });

    }
}
