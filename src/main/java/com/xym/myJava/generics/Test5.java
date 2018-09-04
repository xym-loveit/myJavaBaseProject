package com.xym.myJava.generics;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-08-04 19:40
 */
public class Test5 {
    public static void main(String[] args) {
        Integer[] ints = fun(1, 2, 3, 4, 5);
        print(ints);
    }

    /**
     * 接受可变参数，返回泛型数组
     *
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T[] fun(T... args) {
        return args;
    }

    public static <T> void print(T[] array) {
        for (T t : array) {
            System.out.println(t);
        }
    }
}
