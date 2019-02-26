package com.xym.myJava.extendss;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 代码块实例，静态代码块最先执行(自动执行)，且仅执行一次
 * <p>
 * 非静态代码块在每次创建对象时都执行,比较适合用来初始化对象共性部分
 * <p>
 * 静态代码块中如果只能为定义在后面的变量赋值，无法读取
 *
 * @author xym
 * @create 2019-02-19 11:44
 */
public class CodeBlockDemo {

    static int a = 1;

    {
        System.out.println("非静态代码块!创建每个对象时都执行...");
    }

    static {
        a = 20;
        b = 30;
        System.out.println("静态代码块！a=" + a);
        //System.out.println("静态代码块！b=" + b);
    }

    static int b = 10;

    public static void test() {
        System.out.println("test方法!!!");
    }

    public static void test2() {
        System.out.println("test方法!!!");
    }

    public static void main(String[] args) {
        CodeBlockDemo staticDemo = new CodeBlockDemo();
        CodeBlockDemo staticDemo2 = new CodeBlockDemo();
        staticDemo.test();
        staticDemo2.test2();

        LinkedList<String> strings = new LinkedList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        Iterator<String> iterator = strings.descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
