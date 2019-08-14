package com.xym.myJava.base;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-08 0:50
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Sington instance = Sington.getInstance();
            System.out.println(instance);
        }
    }
}
