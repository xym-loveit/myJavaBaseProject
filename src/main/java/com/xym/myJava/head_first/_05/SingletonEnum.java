package com.xym.myJava.head_first._05;

/**
 * 枚举实现单例模式
 *
 * @author xym
 * @create 2019-04-10 18:50
 */
public class SingletonEnum {

    enum Color {
        RED, GREEN, BLACK;
    }

    public static void main(String[] args) {
        System.out.println(Color.RED);
    }
}
