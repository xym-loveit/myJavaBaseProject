package com.xym.myJava.head_first._13.v3;

/**
 * 鸭子叫声计数器-----装饰器模式
 *
 * @author xym
 * @create 2019-04-18 16:04
 */
public class QuackCounterV3 implements QuackableV3 {

    private QuackableV3 duck;

    /**
     * 鸭叫声计数器
     */
    static int numOfQuacks;

    public QuackCounterV3(QuackableV3 duck) {
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

    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        this.duck.notifyObservers();
    }

    @Override
    public String toString() {
        return "QuackCounterV3{" +
                "duck=" + duck.toString() +
                '}';
    }
}
