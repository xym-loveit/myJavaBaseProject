package com.xym.myJava.thread.joins;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 10:21
 */
public class JoinTest {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);
        threadA.start();
        ThreadC threadC = new ThreadC(threadB);
        threadC.start();
        System.out.println("~~~我来测试一下~~~~");
    }
}
