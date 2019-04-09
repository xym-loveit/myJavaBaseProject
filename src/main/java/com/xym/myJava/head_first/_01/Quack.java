package com.xym.myJava.head_first._01;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-08 15:45
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("实现鸭子呱呱叫");
    }
}
