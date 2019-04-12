package com.xym.myJava.head_first._08;

/**
 * 客户端类
 *
 * @author xym
 * @create 2019-04-12 11:51
 */
public class Cilent {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        System.out.println("=================");
        computer.shutDown();
    }
}
