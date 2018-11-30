package com.xym.myJava.JMM;

/**
 * StackOverflowError 模拟
 *
 * @author xym
 * @create 2018-11-30 14:05
 */
public class StackErrorMock {
    private static int index = 1;

    public void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }
}
