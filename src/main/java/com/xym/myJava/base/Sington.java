package com.xym.myJava.base;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-08 0:48
 */
public class Sington {

    ///**
    // * 饿汉式单例模式
    // */

    //volatile防止重排序，线程可见性
    private static volatile Sington sington = new Sington();

    private Sington() {
    }

    public static Sington getInstance() {
        if (sington == null) {
            synchronized (Sington.class) {
                if (sington == null) {
                    sington = new Sington();
                }
            }
        }
        return sington;
    }


    public static void main(String[] args) {

    }
}
