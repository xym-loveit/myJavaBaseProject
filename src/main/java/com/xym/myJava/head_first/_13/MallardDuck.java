package com.xym.myJava.head_first._13;

/**
 * 绿头鸭
 *
 * @author xym
 * @create 2019-04-18 15:45
 */
public class MallardDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}
