package com.xym.myJava.head_first._13.v3;

import com.xym.myJava.head_first._13.Goose;

/**
 * 创建鹅的适配器，将鹅适配成鸭子，就可以使用鸭子的呱呱叫
 * <p>
 * 体现了适配器模式
 *
 * @author xym
 * @create 2019-04-18 15:57
 */
public class GooseAdapterV3 implements QuackableV3 {

    private Goose goose;
    private Observable observable;

    public GooseAdapterV3(Goose goose) {
        this.goose = goose;
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        this.goose.honk();
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
        return "GooseAdapterV3";
    }
}
