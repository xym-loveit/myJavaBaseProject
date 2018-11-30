package com.xym.myJava.gc;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-11-30 16:10
 */
public class GCTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900 * 1024];
        allocation2 = new byte[6 * 1024 * 1024];
    }
}
