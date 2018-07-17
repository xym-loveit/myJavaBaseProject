package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 13:11
 */
public class SingletonTest {

    private static SingletonTest singletonTest = new SingletonTest();

    private static int x = 0;
    private static int y;


    private SingletonTest() {
        x++;
        y++;
    }

    public static SingletonTest getInstance() {
        return singletonTest;
    }

    public static void main(String[] args) {
        SingletonTest instance = SingletonTest.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
