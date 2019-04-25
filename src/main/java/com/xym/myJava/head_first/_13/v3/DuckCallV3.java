package com.xym.myJava.head_first._13.v3;

/**
 * 鸭鸣器
 *
 * @author xym
 * @create 2019-04-18 15:47
 */
public class DuckCallV3 implements QuackableV3 {

    //辅助对象
    private Observable observable;

    public DuckCallV3() {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Kwak");
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
        return "DuckCallV3";
    }
}
