package com.xym.myJava.concurrent;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-14 13:46
 */
public class CopyOnWriteThread {
    private static CopyOnWriteTestList copyOnWriteTestList = new CopyOnWriteTestList();

    public static void main(String[] args) {

        new ThreadPoolExecutor(10, 10, 10, TimeUnit.MINUTES,
                new ArrayBlockingQueue(11),
                new ThreadPoolExecutor.AbortPolicy()).execute(() -> {
            for (; ; ) {
                int x = 0;
                copyOnWriteTestList.add("test" + x);
                ++x;
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(copyOnWriteTestList.getArray().length);

        /**
         *  启动线程：获取元素
         */
        Runnable runnable = () -> {
            copyOnWriteTestList.get(0);
        };
        runnable.run();
    }

    static class CopyOnWriteTestList {
        private Object[] array;

        public CopyOnWriteTestList() {
            this.array = new Object[0];
        }

        public Object[] getArray() {
            return array;
        }

        public void setArray(Object[] array) {
            this.array = array;
        }

        public void add(String element) {
            int len = array.length;
            Object[] newElements = Arrays.copyOf(array, len + 1);
            newElements[len] = element;
            setArray(newElements);
        }

        public void get(int index) {
            Object[] array = getArray();
            get(array, index);
        }

        /**
         * 此步骤，就是为了验证在获取元素时，array是否会随着元素的添加而改变；
         *
         * @param array
         * @param index
         */
        public void get(Object[] array, int index) {
            for (; ; ) {
                System.out.println("获取方法：" + array.length);
            }
        }

    }
}
