package com.xym.myJava.head_first._13;

/**
 * 鸭子叫声计数器-----装饰器模式
 *
 * @author xym
 * @create 2019-04-18 16:04
 */
public class QuackCounter implements Quackable {

    private Quackable duck;
    /**
     * 鸭叫声计数器
     */
    static int numOfQuacks;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    /**
     * 当quack被调用时，我们就把调用委托给正在装饰的Quackable对象
     */
    @Override
    public void quack() {
        this.duck.quack();
        numOfQuacks++;
    }

    public static int getQuacks() {
        return numOfQuacks;
    }
}
