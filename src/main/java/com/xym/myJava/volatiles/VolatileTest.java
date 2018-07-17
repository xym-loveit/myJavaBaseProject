package com.xym.myJava.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * volatile只能修饰类变量和实例变量
 * <p>
 * <p>
 * 在未使用volatile修饰变量init_value的时候，观察发现Reader线程始终不能结束（Updater线程的更改对Reader线程来说不可见，while条件始终满足）
 * <p>
 * 使用volatile修饰init_value以后，发现2个线程都能正常终结（在未使用volatile修饰init_value时，只有Updater线程可以正常终止）
 *
 * @author xym
 * @create 2018-07-17 22:10
 */
public class VolatileTest {

    final /*volatile*/ int A = 10;//volatile不能用来修饰实例常量
    final static /*volatile*/ int B = 100;//volatile也不能用来修饰类常量

    //init_value的最大值
    final static int MAX_V = 5;
    //init_value的初始值
    static /*volatile*/ int init_value = 0;


    //static volatile int init_value = 0;


    public static void main(String[] args) {

        /**
         * 启动一个Reader线程，当发现localValue和init_value不同时，则输出init_value被修改的信息
         */
        new Thread("Reader") {
            @Override
            public void run() {
                int localValue = init_value;
                while (localValue < MAX_V) {
                    if (localValue != init_value) {
                        System.out.printf("the init_value is updated to [%d]\n", init_value);
                        localValue = init_value;
                    }
                }
            }
        }.start();


        /**
         * 启动一个Updater线程，主要用于对init_value的修改，当localValue>=5
         *时则退出生命周期
         */
        new Thread("Updater") {
            @Override
            public void run() {
                int localValue = init_value;
                while (localValue < MAX_V) {
                    System.out.printf("the int_value will be change to [%d]\n", ++localValue);
                    init_value = localValue;

                    try {
                        //短暂休眠，目的是为了使得Reader线程能够来得及输出变化内容
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}
