package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 11:45
 */
public class Test01 {

    static {
        System.out.println("class init!");
    }

    /**
     * 访问类的静态属性，会导致类初始化（static块被执行）
     */
    public static int x = 10;

    /**
     * 访问类的静态常量（基本类型）不会导致类的初始化
     */
    public final static int y = 10;

    /**
     * 访问类的静态方法会导致类的初始化
     */
    public static void method01() {

    }
}
