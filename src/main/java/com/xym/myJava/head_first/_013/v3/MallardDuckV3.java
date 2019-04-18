package com.xym.myJava.head_first._013.v3;

/**
 * 绿头鸭
 *
 * @author xym
 * @create 2019-04-18 15:45
 */
public class MallardDuckV3 implements QuackableV3 {

    //每个QuackableV3都有一个Observable辅助实例
    private Observable observable;

    public MallardDuckV3() {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Quack!");
        //每当呱呱叫时，都需要让观察者知道
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }


    @Override
    public String toString() {
        return "MallardDuckV3";
    }
}
