package com.xym.myJava.head_first._013.v3;

/**
 * 橡皮鸭
 *
 * @author xym
 * @create 2019-04-18 15:48
 */
public class RubberDuckV3 implements QuackableV3 {
    //辅助对象
    private Observable observable;

    public RubberDuckV3() {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Squeak!");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        this.observable.notifyObservers();
    }

    @Override
    public String toString() {
        return "RubberDuckV3";
    }
}
