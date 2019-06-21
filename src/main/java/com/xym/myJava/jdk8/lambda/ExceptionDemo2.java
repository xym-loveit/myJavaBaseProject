package com.xym.myJava.jdk8.lambda;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 异常的处理------------------比较经典
 *
 * @author xym
 * @create 2019-06-21 11:38
 */
public class ExceptionDemo2 {

    /**
     * @param first   产生结果
     * @param second  处理结果
     * @param handler 处理过程中的异常--统一处理所有异常
     * @param <T>
     */
    public static <T> void doInOrderAsync(Supplier<T> first, Consumer<T> second, Consumer<Throwable> handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result);
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        };
        thread.start();
    }

    /**
     * @param first            产生结果
     * @param secondAndHandler 处理结果及其处理first中产生的异常
     * @param handler          处理second宏产生的异常
     * @param <T>
     */
    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> secondAndHandler, Consumer<Throwable> handler) {
        new Thread() {
            @Override
            public void run() {
                T result = null;
                Throwable throwable = null;
                try {
                    result = first.get();
                } catch (Throwable t) {
                    throwable = t;
                }
                try {
                    secondAndHandler.accept(result, throwable);
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        }.start();
    }


    /**
     * 异常内部处理不了的情况，一般希望抛出时采用
     *
     * @param first            产生结果
     * @param secondAndHandler 处理结果及其处理first中产生的异常
     * @param handler          处理second宏产生的异常
     * @param <T>
     */
    public static <T> void doInOrderAsyncWithExcep(Supplier<T> first, BiConsumer<T, Throwable> secondAndHandler, ConsumerWithExcep<Throwable> handler) throws Exception {
        Callable callable = () -> {
            T result = null;
            Throwable throwable = null;
            try {
                result = first.get();
            } catch (Throwable t) {
                throwable = t;
            }
            try {
                secondAndHandler.accept(result, throwable);
            } catch (Throwable t) {
                handler.accept(t);
            }
            return null;
        };
        //FutureTask futureTask = new FutureTask(callable);
        //new Thread(futureTask).start();
        callable.call();
    }

    public static void main(String[] args) throws Exception {
        //care1();
        //care2();
        doInOrderAsyncWithExcep(() -> {
            if (2 == 2) {
                //制造第一处异常
                throw new RuntimeException("----------------");
            }
            return "Hello";
        }, (s, t) -> {
            //第一个步骤正常
            if (!Optional.ofNullable(t).isPresent()) {
                s.charAt(10);
            } else {
                System.out.println("11111-excep");
                //处理异常中继续制造异常
                //t.getMessage().charAt(100);
                throw new RuntimeException("-----继续异常-------", t);
            }
        }, throwable -> {
            System.out.println("222--excep");
            System.out.println(throwable);
            //if (1 == 1) {
            throw new Exception("222--excep", throwable);
            //}
        });
    }

    private static void care2() {
        doInOrderAsync(() -> {
            if (1 == 1) {
                //制造第一处异常
                throw new RuntimeException("----------------");
            }
            return "Hello";
        }, (s, t) -> {
            //第一个步骤正常
            if (!Optional.ofNullable(t).isPresent()) {
                s.charAt(10);
            } else {
                System.out.println("11111-excep");
                //处理异常中继续制造异常
                //t.getMessage().charAt(100);
                throw new RuntimeException("-----继续异常-------", t);
            }
        }, throwable -> {
            System.out.println("222--excep");
            System.out.println(throwable);
            //throw throwable;
        });
    }

    private static void care1() {
        doInOrderAsync(() -> {
            //假如在第一个生产环节就出现了异常，则有第二个lambda表达式来处理该异常
            System.out.println("make execp");
            if (1 == 1) {
                throw new RuntimeException("ceshi");
            }
            return 1;
        }, (o, t) -> {
            //System.out.println(o);
            //throw t;
            //System.out.println(o.toString());
            //参数o为正常生产的对象，t为异常对象，2者是互斥关系
            Optional.ofNullable(t).ifPresent(throwable -> {
                System.out.println("111111111");
                throwable.printStackTrace();
                return;
            });
            //没有出现异常时应该执行
            Optional.ofNullable(o).ifPresent(System.out::println);
        }, (e) -> {
            //针对第二个处理环节出现异常时执行
            System.out.println("222");
            e.printStackTrace();
        });
    }

}
