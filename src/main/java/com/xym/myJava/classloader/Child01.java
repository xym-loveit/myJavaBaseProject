package com.xym.myJava.classloader;

import java.util.Random;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 11:50
 */
public class Child01 extends Parent01 {
    static {
        System.out.println("child init!");
    }

    public static int x = 10;

    /**
     * 虽然是静态常量但因为只有运行时才能确定也会导致类的初始化
     */
    public static final int RANDOM = new Random().nextInt(10);
}
