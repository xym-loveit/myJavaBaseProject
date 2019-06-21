package com.xym.myJava.jdk8.lambda;

import java.util.function.IntConsumer;

/**
 * lambda表达式的参数
 *
 * @author xym
 * @create 2019-06-21 11:15
 */
public class Parameters {
    public static void main(String[] args) {
        /**
         * 当前带参数的action可能需要知道操作发生的哪次迭代中，即为参数i
         */
        repeat(10, i -> System.out.println("Countdown: " + (9 - i)));
        /**
         * 第二种版本，不需要过程接受任何参数
         */
        repeat(10, () -> System.out.println("Hello, World!"));
    }

    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }

    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            System.out.println(Thread.currentThread().getName());
            action.run();
        }
    }
}
